package com.example.unionline.Views.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unionline.Common;
import com.example.unionline.DTO.Attendance;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Lesson;
import com.example.unionline.DTO.User;
import com.example.unionline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentAttendanceActivity extends AppCompatActivity {

    TextView tvActivityName, tvClassName, tvRoom, tvState, tvFullTime, tvAttendanceState,
            tvTeacherName, tvLessonName, tvLessonDescription;

    ImageView ivTeacherAvatar;

    Attendance attendance;

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        // Get data from intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            attendance = (Attendance) bundle.getSerializable("attendance");
        }

        mapView();
        mapViewValue();
    }

    private void mapView(){
        tvActivityName = (TextView) findViewById(R.id.activity_name);
        tvClassName = (TextView) findViewById(R.id.tvClassName);
        tvRoom = (TextView) findViewById(R.id.tvRoom);
        tvState = (TextView) findViewById(R.id.tvState);
        tvFullTime = (TextView) findViewById(R.id.tvClassDate);
        tvAttendanceState = (TextView) findViewById(R.id.tvAttendanceState);
        tvTeacherName = (TextView) findViewById(R.id.tvTeacherName);
        tvLessonName = (TextView) findViewById(R.id.tvLessonName);
        tvLessonDescription = (TextView) findViewById(R.id.tvLessonDescription);
        ivTeacherAvatar = (ImageView) findViewById(R.id.ivTeacherAvatar);
    }

    private void mapViewValue(){

        tvActivityName.setText("Lớp học");

        if(attendance.getState().equals("Chưa điểm danh")){
            tvState.setText("Chưa học");
        } else {
            tvState.setText("Đã học");
        }
        tvFullTime.setText(attendance.getFullDate()  + " | " + attendance.getFullTime());

        // Get class
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Classes").child(Common.semester.getSemesterId()).child(attendance.getClassId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                }
                else {
                    Class aClass = task.getResult().getValue(Class.class);

                    tvClassName.setText(aClass.getClassName());
                    tvRoom.setText(aClass.getRoom());
                }
            }
        });

        // Get teacher
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("User").child("18110234").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                }
                else {
                    User user = task.getResult().getValue(User.class);

                    tvTeacherName.setText(user.getName());
                }
            }
        });

        // Get lesson
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Lesson").child(Common.semester.getSemesterId()).child(attendance.getLessonId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                }
                else {
                    Lesson lesson = task.getResult().getValue(Lesson.class);

                    tvLessonName.setText(lesson.getName());
                    tvLessonDescription.setText(lesson.getDescription());
                }
            }
        });
    }
}