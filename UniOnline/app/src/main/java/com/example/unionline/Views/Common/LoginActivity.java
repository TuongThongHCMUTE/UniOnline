package com.example.unionline.Views.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.unionline.Common;
import com.example.unionline.DTO.AbsenceApplication;
import com.example.unionline.DTO.Attendance;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.Enrollment;
import com.example.unionline.DTO.Lesson;
import com.example.unionline.DTO.Parent_Student;
import com.example.unionline.DTO.Semester;
import com.example.unionline.DTO.User;
import com.example.unionline.R;
import com.example.unionline.Views.Students.StudentMainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Spinner spRoles;
    Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setSpinnerItems();
        addData();

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spRoles.getSelectedItem().toString() == Common.roleStudent){
                    startActivity(new Intent(LoginActivity.this, StudentMainActivity.class));
                    return;
                }
            }
        });
    }

    /**
     * Set list Roles for Spinner
     */
    private void setSpinnerItems(){
        spRoles = findViewById(R.id.spRoles);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(Common.roleAdmin);
        arrayList.add(Common.roleManager);
        arrayList.add(Common.roleTeacher);
        arrayList.add(Common.roleStudent);
        arrayList.add(Common.roleParent);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoles.setAdapter(arrayAdapter);
    }

    private void addData(){
        DatabaseReference mDatabase;

        //Add user
        User user = new User();
        user.setUserId("18110234");
        user.setEmail("Le Nhat Tuong");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(user.getUserId()).setValue(user);

        Semester semester = new Semester();
        semester.setSemesterId("2020_2021_HK1");
        semester.setSemesterName("Hoc ky 1, nam hoc 2020-2021");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Semesters").child(semester.getSemesterId()).setValue(semester);

        Parent_Student parent_student = new Parent_Student("10110101","18110234");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Parent_Students").child(parent_student.getParentId()).setValue(parent_student);

        Class class_s = new Class();
        class_s.setClassId("MP");
        class_s.setSemesterId(semester.getSemesterId());
        class_s.setClassName("Lập trình di động");
        class_s.setCapacity(40);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Classes").child(semester.getSemesterId()).child(class_s.getClassId()).setValue(class_s);

        //Add for teachers
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Teachers").child("Courses").child(semester.getSemesterId()).child(class_s.getClassId()).setValue(class_s);

        //Add for students
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Students").child("Courses").child(semester.getSemesterId()).child(class_s.getClassId()).setValue(class_s);

        //Add enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setClassId(class_s.getClassId());
        enrollment.setStudentId(user.getUserId());
        enrollment.setStudentName(user.getName());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Enrollments").child(semester.getSemesterId()).child(class_s.getClassId()).child(enrollment.getStudentId()).setValue(enrollment);

        //Add lesson
        Lesson lesson = new Lesson();
        lesson.setClassId(class_s.getClassId());
        lesson.setLessonId("1");
        lesson.setName("Web API");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Lessons").child(semester.getSemesterId()).child(class_s.getClassId()).child(lesson.getLessonId()).setValue(lesson);

        Attendance attendance = new Attendance();
        attendance.setClassId(class_s.getClassId());
        attendance.setLessonId(lesson.getLessonId());
        attendance.setStudentId(user.getUserId());
        attendance.setState("Trễ");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Attendances").child(semester.getSemesterId()).child(class_s.getClassId()).child(attendance.getLessonId()).child(attendance.getStudentId()).setValue(enrollment);

        AbsenceApplication aa = new AbsenceApplication();
        aa.setId(0);
        aa.setClassId(class_s.getClassId());
        aa.setStudentId(user.getUserId());
        aa.setReason("So late");
        aa.setState(0);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("AbsenceApplications").child(semester.getSemesterId()).child(class_s.getClassId()).child(String.valueOf(aa.getId())).setValue(aa);

        //Set ab for students
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Students").child("AbsenceApplications").child(String.valueOf(aa.getId())).setValue(aa);
    }
}