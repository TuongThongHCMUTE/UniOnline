package com.example.unionline.Views.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unionline.Adapters.Students.AbsenceApplicationAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.AbsenceApplication;
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

public class StudentAbsenceApplicationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<AbsenceApplication> absenceApplications;
    AbsenceApplicationAdapter adapter;
    DatabaseReference mDatabase;

    ImageView backIcon;
    TextView tvActivityName;
    private FloatingActionButton fabAddNAA;

    private AbsenceApplicationAdapter.RecyclerViewClickListener listener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_absence_application);

        // Set activity name on toolbar
        tvActivityName = (TextView) findViewById(R.id.activity_name);
        tvActivityName.setText("Xin nghỉ");

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
    }

    private void openDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_absence_application, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setView(view);
        alertDialog.show();
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