package com.example.unionline.Views.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.unionline.Adapters.Students.AbsenceApplicationAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.AbsenceApplication;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Enrollment;
import com.example.unionline.R;
import com.example.unionline.Views.Teachers.TeacherAddNotificationActivity;
import com.example.unionline.Views.Teachers.TeacherListNotificationsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class StudentAbsenceApplicationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AbsenceApplication> absenceApplications;
    AbsenceApplicationAdapter adapter;
    DatabaseReference mDatabase;

    ImageView backIcon;
    TextView tvActivityName;
    private FloatingActionButton fabAddNAA;

    ArrayList<Enrollment> enrollments;
    ArrayList<String> classNames;

    private AbsenceApplicationAdapter.RecyclerViewClickListener listener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_absence_application);

        // Set activity name on toolbar
        tvActivityName = (TextView) findViewById(R.id.activity_name);
        tvActivityName.setText("Tạo đơn xin nghỉ");

        // Set event click for backIcon on toolbar
        // When click backIcon: finish this activity
        backIcon = (ImageView) findViewById(R.id.left_icon);
        backIcon.setOnClickListener((View v) -> {
            this.finish();
        });

        fabAddNAA = (FloatingActionButton) findViewById(R.id.fabAddNAA);
        fabAddNAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        // Set event click for item in list absenceApplications
        setOnClickListener();
        setRecyclerView();
        getListClasses();
    }

    private void openDialog() {
        EditText edtContent;
        Spinner spClassNames;
        TextView tvDateOff;
        ImageView ivClose;
        Button btSave;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_absence_application, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);

        edtContent = view.findViewById(R.id.edtContent);
        spClassNames = view.findViewById(R.id.spClassNames);
        tvDateOff = view.findViewById(R.id.tvDateOff);
        ivClose = view.findViewById(R.id.iv_close);
        btSave = view.findViewById(R.id.btSave);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClassNames.setAdapter(arrayAdapter);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        tvDateOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar(tvDateOff);
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //spClassNames.indexOfChild(spClassNames.get)
                Enrollment enrollment = enrollments.get(spClassNames.getSelectedItemPosition());
                String content = edtContent.getText().toString();

                // Add absence application
                mDatabase = FirebaseDatabase.getInstance().getReference().child("AbsenceApplications").child(Common.semester.getSemesterId());
                String key = mDatabase.push().getKey();
                String dateOff = tvDateOff.getText().toString();

                AbsenceApplication aa = new AbsenceApplication(key, enrollment.getClassId(), enrollment.getClassName(),
                        enrollment.getFullDate(), Common.user.getUserId(), Common.user.getName(),content, dateOff, Common.AA_WAIT_FOR_APPROVAL);

                mDatabase.child(aa.getId()).setValue(aa);
                alertDialog.cancel();

            }
        });


        alertDialog.show();
    }
    private void openCalendar(TextView tvDate) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar_date = Calendar.getInstance();
                calendar_date.set(year,month,dayOfMonth);

                String date = DateFormat.format("dd/MM/yyyy", calendar_date).toString();

                tvDate.setText(date);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void getListClasses() {
        enrollments = new ArrayList<Enrollment>();
        classNames = new ArrayList<String>();

        // Fill data from Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Enrollments").child(Common.semester.getSemesterId());
        Query query = mDatabase.orderByChild("studentId").equalTo(Common.user.getUserId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                enrollments.clear();
                classNames.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Enrollment enrollment = dataSnapshot.getValue(Enrollment.class);
                    enrollments.add(enrollment);
                    classNames.add(enrollment.getClassName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setOnClickListener() {
        listener = new AbsenceApplicationAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View v, int position) {
                /*Intent intent = new Intent(StudentAbsenceApplicationActivity.this, StudentClassDetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("enrollment", (Serializable) absenceApplications.get(position));
                intent.putExtras(bundle);

                startActivity(intent);*/
            }
        };
    }

    private void setRecyclerView() {
        // Initialize
        recyclerView = findViewById(R.id.rvAbsenceApplication);

        absenceApplications  = new ArrayList<>();

        adapter = new AbsenceApplicationAdapter(this, absenceApplications, listener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set adapter for recycler view
        recyclerView.setAdapter(adapter);

        // Fill data from Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("AbsenceApplications").child(Common.semester.getSemesterId());
        Query query = mDatabase.orderByChild("studentId").equalTo(Common.user.getUserId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                absenceApplications.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    AbsenceApplication absenceApplication = dataSnapshot.getValue(AbsenceApplication.class);
                    absenceApplications.add(absenceApplication);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}