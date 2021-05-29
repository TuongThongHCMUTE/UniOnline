package com.example.unionline.Views.Teachers.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.unionline.Adapters.Students.NotificationAdapter;
import com.example.unionline.Adapters.Teachers.UpdateAttendantAdapter;
import com.example.unionline.Common;
import com.example.unionline.DTO.Attendance;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Notification;
import com.example.unionline.R;
import com.example.unionline.Sorter.NotificationDateSorter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TeacherAttendantUpdateActivity extends AppCompatActivity {

    private String currentClassId;
    RecyclerView recyclerView;
    ArrayList<Attendance> lisAttendances;
    UpdateAttendantAdapter adapter;
    DatabaseReference mDatabase;
    private UpdateAttendantAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendant_update);

        // Get data from intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            currentClassId = bundle.getString("classId");
        }

        setOnClickListener();
        setRecyclerView();
    }

    private void setOnClickListener() {
        listener = new UpdateAttendantAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View itemView, int adapterPosition) {

            }
        };
    }

    private void setRecyclerView(){
        recyclerView = findViewById(R.id.rvAttendance);

        lisAttendances = new ArrayList<>();

        adapter = new UpdateAttendantAdapter(this, lisAttendances, listener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);

        // Get data from firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("Attendances").child(Common.semester.getSemesterId());
        Query query = mDatabase.orderByChild("classId").equalTo(currentClassId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lisAttendances.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Attendance attendance = dataSnapshot.getValue(Attendance.class);

                    lisAttendances.add(attendance);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}