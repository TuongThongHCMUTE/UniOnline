package com.example.unionline.Views.Common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.unionline.DTO.Notification;
import com.example.unionline.DTO.Parent_Student;
import com.example.unionline.DTO.Semester;
import com.example.unionline.DTO.User;
import com.example.unionline.R;
import com.example.unionline.Views.Students.StudentMainActivity;
import com.example.unionline.Views.Teachers.TeacherMainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LoginActivity extends AppCompatActivity {

    Spinner spRoles;
    Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setSpinnerItems();

        //addDataV2();
        Common common = new Common();

        Common.semester = new Semester();
        Common.semester.setSemesterId("2020_2021_HK1");
        Common.semester.setSemesterName("Hoc ky 1, nam hoc 2020-2021");

        Common.user = new User();
        Common.user.setUserId("18110234");

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spRoles.getSelectedItem().toString() == Common.userRoles.get(Common.roleStudent)){
                    startActivity(new Intent(LoginActivity.this, StudentMainActivity.class));
                    return;
                } else if(spRoles.getSelectedItem().toString() == Common.userRoles.get(Common.roleTeacher)){
                    startActivity(new Intent(LoginActivity.this, TeacherMainActivity.class));
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
        arrayList.add(Common.userRoles.get(Common.roleAdmin));
        arrayList.add(Common.userRoles.get(Common.roleManager));
        arrayList.add(Common.userRoles.get(Common.roleTeacher));
        arrayList.add(Common.userRoles.get(Common.roleStudent));
        arrayList.add(Common.userRoles.get(Common.roleParent));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoles.setAdapter(arrayAdapter);
    }

    private void addDataV2(){
        DatabaseReference mDatabase;
        String key;

        Semester semester = new Semester();
        semester.setSemesterId("2020_2021_HK1");
        semester.setSemesterName("Hoc ky 1, nam hoc 2020-2021");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Semesters").child(semester.getSemesterId()).setValue(semester);

        //Add user
        User user = new User();
        user.setUserId("18110234");
        user.setEmail("Le Nhat Tuong");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(user.getUserId()).setValue(user);

        Parent_Student parent_student = new Parent_Student("10110101","18110234");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Parent_Students");
        key = mDatabase.push().getKey();
        parent_student.setId(key);
        mDatabase.child(key).setValue(parent_student);

        Class class_s = new Class();
        class_s.setTeacherId(user.getUserId());
        class_s.setSemesterId(semester.getSemesterId());
        class_s.setClassName("Lập trình di động");
        class_s.setCapacity(40);
        mDatabase = FirebaseDatabase.getInstance().getReference("Classes").child(semester.getSemesterId());
        key = mDatabase.push().getKey();
        class_s.setClassId(key);
        mDatabase.child(class_s.getClassId()).setValue(class_s);

        //Add enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setClassId(class_s.getClassId());
        enrollment.setStudentId(user.getUserId());
        enrollment.setStudentName(user.getName());
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Enrollments").child(semester.getSemesterId());
        key = mDatabase.push().getKey();
        enrollment.setId(key);
        mDatabase.child(key).setValue(enrollment);

        //Add lesson
        Lesson lesson = new Lesson();
        lesson.setLessonId("1");
        lesson.setName("Web API");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Lessons").child(semester.getSemesterId());
        key = mDatabase.push().getKey();
        lesson.setClassId(key);
        mDatabase.child(key).setValue(lesson);

        // Add attendance
        Attendance attendance = new Attendance();
        attendance.setClassId(class_s.getClassId());
        attendance.setLessonId(lesson.getLessonId());
        attendance.setStudentId(user.getUserId());
        attendance.setState(2);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Attendances").child(semester.getSemesterId());
        key = mDatabase.push().getKey();
        attendance.setId(key);
        mDatabase.child(key).setValue(attendance);

        // Add absence application
        AbsenceApplication aa = new AbsenceApplication();
        aa.setClassId(class_s.getClassId());
        aa.setStudentId(user.getUserId());
        aa.setReason("So late");
        aa.setState(0);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("AbsenceApplications").child(semester.getSemesterId());
        key = mDatabase.push().getKey();
        aa.setId(key);
        mDatabase.setValue(aa);

        Notification notification = new Notification("1", "1", "1", R.drawable.logo_hcmute,"1", "1", "1");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Notifications").child(String.valueOf(notification.getId())).setValue(notification);
    }

    private void addData(){
        DatabaseReference mDatabase;

        Semester semester = new Semester();
        semester.setSemesterId("2020_2021_HK1");
        semester.setSemesterName("Hoc ky 1, nam hoc 2020-2021");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Semesters").child(semester.getSemesterId()).setValue(semester);

        //Add user
        User user = new User();
        user.setUserId("18110234");
        user.setEmail("Le Nhat Tuong");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(user.getUserId()).setValue(user);

        Parent_Student parent_student = new Parent_Student("10110101","18110234");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Parent_Students").child(parent_student.getParentId()).setValue(parent_student);

        Class class_s = new Class();
        /*
        class_s.setClassId("SE");
        class_s.setSemesterId(semester.getSemesterId());
        class_s.setClassName("Công nghệ phần mềm");
        class_s.setCapacity(40);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Classes").child(semester.getSemesterId()).child(class_s.getClassId()).setValue(class_s); */

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
        lesson.setLessonId("2");
        lesson.setName("Recycler View");
        lesson.setStatus(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Lessons").child(semester.getSemesterId()).child(class_s.getClassId()).child(lesson.getLessonId()).setValue(lesson);

        Attendance attendance = new Attendance();
        attendance.setClassId(class_s.getClassId());
        attendance.setLessonId(lesson.getLessonId());
        attendance.setStudentId(user.getUserId());
        attendance.setState(2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Attendances").child(semester.getSemesterId()).child(class_s.getClassId()).child(attendance.getLessonId()).child(attendance.getStudentId()).setValue(enrollment);

        AbsenceApplication aa = new AbsenceApplication();
        //aa.setId(0);
        aa.setClassId(class_s.getClassId());
        aa.setStudentId(user.getUserId());
        aa.setReason("So late");
        aa.setState(0);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("AbsenceApplications").child(semester.getSemesterId()).child(class_s.getClassId()).child(String.valueOf(aa.getId())).setValue(aa);

        //Set ab for students
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Students").child("AbsenceApplications").child(String.valueOf(aa.getId())).setValue(aa);

        Common.class_ = class_s;
        Common.semester = semester;

        Notification notification = new Notification("1", "1", "1", R.drawable.logo_hcmute,"1", "1", "1");


        ArrayList<User> users;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Notifications").child(String.valueOf(notification.getId())).setValue(notification);
    }
}