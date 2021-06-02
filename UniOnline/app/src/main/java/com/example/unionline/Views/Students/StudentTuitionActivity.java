package com.example.unionline.Views.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unionline.Adapters.Students.TuitionAdapter;
import com.example.unionline.Adapters.Students.TuitionAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.Enrollment;
import com.example.unionline.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class StudentTuitionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Enrollment> enrollments;
    TuitionAdapter adapter;
    DatabaseReference mDatabase;

    ImageView backIcon;
    TextView tvActivityName, tvPaidTuition, tvMuchPaidTuition;

    NumberFormat formatter;
    String formattedNumber;

    Boolean isPayClassTuition;

    CardView cvPaidTuition, cvMuchPaidTuition;

    private TuitionAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tuition);

        formatter = new DecimalFormat("#,###");
        isPayClassTuition = true;

        // Set activity name on toolbar
        tvActivityName = (TextView) findViewById(R.id.activity_name);
        tvActivityName.setText("Học phí");

        tvPaidTuition = findViewById(R.id.tvPaidTuition);
        tvMuchPaidTuition = findViewById(R.id.tvMuchPaidTuition);

        cvPaidTuition = findViewById(R.id.cvPaidTuition);
        cvPaidTuition.setOnClickListener((View v) -> {
            isPayClassTuition = true;
            setDataForRecyclerView();
        });

        cvMuchPaidTuition = findViewById(R.id.cvMuchPaidTuition);
        cvMuchPaidTuition.setOnClickListener((View v) -> {
            isPayClassTuition = false;
            setDataForRecyclerView();
        });

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
        listener = new TuitionAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View v, int position) {
            }
        };
    }

    private void setRecyclerView() {
        // Initialize
        recyclerView = findViewById(R.id.rvStudentTuiton);

        enrollments  = new ArrayList<>();

        setDataForRecyclerView();

        adapter = new TuitionAdapter(this, enrollments, listener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Set adapter for recycler view
        recyclerView.setAdapter(adapter);
    }

    private void setDataForRecyclerView(){
        // Fill data from Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Enrollments").child(Common.semester.getSemesterId());
        Query query = mDatabase.orderByChild("studentId").equalTo(Common.user.getUserId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                enrollments.clear();
                int totalPaidTuition = 0;
                int totalMuchPaidTuition = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Enrollment enrollment = dataSnapshot.getValue(Enrollment.class);

                    if(enrollment.isPayClassTuition() == isPayClassTuition){
                        enrollments.add(enrollment);
                    }

                    if(enrollment.isPayClassTuition()){
                        totalPaidTuition+=enrollment.getClassTuition();
                    } else {
                        totalMuchPaidTuition += enrollment.getClassTuition();
                    }

                    formattedNumber = formatter.format(totalPaidTuition);
                    tvPaidTuition.setText(formattedNumber + " VND");

                    formattedNumber = formatter.format(totalMuchPaidTuition);
                    tvMuchPaidTuition.setText(formattedNumber + " VND");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}