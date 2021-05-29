package com.example.unionline.Views.Teachers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.unionline.Adapters.Students.NotificationAdapter;
import com.example.unionline.Adapters.Teachers.UpdateAttendantAdapter;
import com.example.unionline.Common;
import com.example.unionline.DAO.AttendanceDAO;
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
import java.util.stream.Collectors;

public class TeacherAttendantUpdateActivity extends AppCompatActivity {

    private String currentClassId;

    RecyclerView recyclerView;
    ArrayList<Attendance> lisAttendances;
    UpdateAttendantAdapter adapter;
    DatabaseReference mDatabase;

    private TextView tvSum, tvLate, tvWithPermission, tvWithoutPermission, tvActivityName;
    private RadioButton rbOnTime, rbLate, rbWithPermission, rbWithoutPermission;
    private ImageView backIcon;
    private int sum, lateCount, withPermissionCount, withoutPermissionCount;
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

        mappingView();
        setOnClickListener();
        setRecyclerView();
    }


    private void mappingView(){
        setToolbar();

        tvSum = (TextView) findViewById(R.id.txtSum);
        tvLate = (TextView) findViewById(R.id.txtLate);
        tvWithPermission = (TextView) findViewById(R.id.txtWithPermission);
        tvWithoutPermission = (TextView) findViewById(R.id.txtWithoutPermission);
    }

    private void setToolbar() {
        // Set activity name on toolbar
        tvActivityName = (TextView) findViewById(R.id.activity_name);
        tvActivityName.setText("Điểm danh");

        // Set event click for backIcon on toolbar
        // When click backIcon: finish this activity
        backIcon = (ImageView) findViewById(R.id.left_icon);
        backIcon.setOnClickListener((View v) -> {
            this.finish();
        });
    }

    private void setOnClickListener() {
        listener = new UpdateAttendantAdapter.RecyclerViewClickListener() {
            @Override
            public void onCLick(View itemView, int adapterPosition) {
                Attendance attendance = lisAttendances.get(adapterPosition);

                rbOnTime = (RadioButton) itemView.findViewById(R.id.rbOnTime);
                rbLate = (RadioButton) itemView.findViewById(R.id.rbLate);
                rbWithPermission = (RadioButton) itemView.findViewById(R.id.rbWithPermission);
                rbWithoutPermission = (RadioButton) itemView.findViewById(R.id.rbWithoutPermission);

                if (rbOnTime.isChecked()) {
                    attendance.setState(Common.ATTENDANCE_ON_TIME);
                } else if (rbLate.isChecked()) {
                    attendance.setState(Common.ATTENDANCE_LATE);
                } else if (rbWithPermission.isChecked()) {
                    attendance.setState(Common.ATTENDANCE_WITH_PERMISSION);
                } else if (rbWithoutPermission.isChecked()) {
                    attendance.setState(Common.ATTENDANCE_WITHOUT_PERMISSION);
                } else {
                    attendance.setState(Common.ATTENDANCE_NOT_YET);
                }

                AttendanceDAO.getInstance().changeAttendanceState(attendance);

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
                updateStatistic();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateStatistic() {
        lateCount = lisAttendances
                .stream().filter(attendance -> attendance.getState() == Common.ATTENDANCE_LATE)
                .collect(Collectors.toList()).size();
        withPermissionCount = lisAttendances
                .stream().filter(attendance -> attendance.getState() == Common.ATTENDANCE_WITH_PERMISSION)
                .collect(Collectors.toList()).size();
        withoutPermissionCount = lisAttendances
                .stream().filter(attendance -> attendance.getState() == Common.ATTENDANCE_WITHOUT_PERMISSION)
                .collect(Collectors.toList()).size();
        sum = lisAttendances
                .stream().filter(attendance -> attendance.getState() == Common.ATTENDANCE_ON_TIME)
                .collect(Collectors.toList()).size() + lateCount;

        tvSum.setText(String.valueOf(sum) + "/" + String.valueOf(lisAttendances.size()));
        tvLate.setText(String.valueOf(lateCount));
        tvWithPermission.setText(String.valueOf(withPermissionCount));
        tvWithoutPermission.setText(String.valueOf(withoutPermissionCount));
    }
}