package com.example.unionline.Views.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unionline.Adapters.Students.EnrollmentAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Enrollment;
import com.example.unionline.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentEnrollmentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Enrollment> enrollments;
    EnrollmentAdapter adapter;
    DatabaseReference mDatabase;
    
    ImageView backIcon;
    TextView tvActivityName;

    private EnrollmentAdapter.RecyclerViewClickListener listener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class);

        // Set activity name on toolbar
        tvActivityName = (TextView) findViewById(R.id.activity_name);
        tvActivityName.setText("Lớp học");

        // Set event click for backIcon on toolbar
        // When click backIcon: finish this activity
        backIcon = (ImageView) findViewById(R.id.left_icon);
        backIcon.setOnClickListener((View v) -> {
            this.finish();
        });

        // Set event click for item in list enrollments
        setOnClickListener();
        setRecyclerView();
    }

    private void setOnClickListener() {
        listener = new EnrollmentAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View v, int position) {
            }
        };
    }

    private void setRecyclerView() {

        /*//Add enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setClassId("-Mah1bXNZ1gVfLAtjT7w");
        enrollment.setClassName("Lập trình di động");
        enrollment.setClassRoom("E1 - 503");
        enrollment.setFullDate("20/05/2021 | 07:00 - 10:30");
        enrollment.setStudentId("18110234");
        enrollment.setStudentName("Lê Nhật Tường");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Enrollments").child(Common.semester.getSemesterId());
        String key = mDatabase.push().getKey();
        enrollment.setId(key);
        mDatabase.child(key).setValue(enrollment);*/

        // Initialize
        recyclerView = findViewById(R.id.rvClass);

        enrollments  = new ArrayList<>();

        adapter = new EnrollmentAdapter(this, enrollments, listener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set adapter for recycler view
        recyclerView.setAdapter(adapter);

        // Fill data from Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Enrollments").child(Common.semester.getSemesterId());
        Query query = mDatabase.orderByChild("studentId").equalTo(Common.user.getUserId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                enrollments.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Enrollment enrollment = dataSnapshot.getValue(Enrollment.class);
                    enrollments.add(enrollment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}