package com.example.unionline.DAO;

import androidx.annotation.NonNull;

import com.example.unionline.Common;
import com.example.unionline.DTO.Attendance;
import com.example.unionline.DTO.ClassModel1;
import com.example.unionline.DTO.Enrollment;
import com.example.unionline.DTO.Lesson;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    String path;
    DatabaseReference mDataBase;

    private static EnrollmentDAO instance;
    boolean error;
    public static EnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new EnrollmentDAO();
        }
        return instance;
    }

    public static void setInstance(EnrollmentDAO instance) {
        EnrollmentDAO.instance = instance;
    }

    public EnrollmentDAO() { path = "Enrollments"; }

    public void update(Enrollment enrollment) {
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child(path)
                .child(Common.semester.getSemesterId())
                .child(String.valueOf(enrollment.getId()))
                .setValue(enrollment);
    }

    public boolean deleteEnrollMent(Enrollment enrollment,ClassModel1 classModel1) {
        try {
            System.out.println(classModel1.getSemesterId()+enrollment.getId()+ "Is there");
            mDataBase = FirebaseDatabase.getInstance().getReference();
            mDataBase.child(path).child(classModel1.getSemesterId()).child(enrollment.getId()).removeValue();
            return true;
        } catch (Error error){
            return false;
        }
    }
    public boolean DeleteAllAllEnrollMentByClassModel(ClassModel1 classModel1)
    {
        error=true;
        System.out.println("Class Id"+classModel1.getClassId());
        mDataBase = FirebaseDatabase.getInstance().getReference("Enrollments").child(classModel1.getSemesterId());
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Enrollment enrollment= dataSnapshot.getValue(Enrollment.class);
                    if(enrollment.getClassId().equals(classModel1.getClassId()))
                        deleteEnrollMent(enrollment,classModel1);

                }
                error=false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return error;
    }
    public void setValude(Enrollment enrollment,ClassModel1 classModel1)
    {
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("Enrollments").child(classModel1.getSemesterId()).child(enrollment.getId()).setValue(enrollment);
    }

//Update data EnrollMent with class new.
    public boolean UpdateEnrollmentByClassModel(ClassModel1 classModelOld,ClassModel1 classModelNew)
    {
        error=true;
        mDataBase = FirebaseDatabase.getInstance().getReference("Enrollments").child(classModelOld.getSemesterId());
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Enrollment enrollment= dataSnapshot.getValue(Enrollment.class);
                    if(enrollment.getClassId().equals(classModelOld.getClassId()))
                    {
                        enrollment.setClassId(classModelNew.getClassId());
                        enrollment.setClassName(classModelNew.getClassName());
                        enrollment.setClassRoom(classModelNew.getRoom());
                        String fulldate=classModelNew.getStartDate()+" | "+changeTime(classModelNew.getStartTime())+" - "+changeTime(classModelNew.getEndTime());
                        enrollment.setFullDate(fulldate);
                        setValude(enrollment,classModelOld);

                    }

                }
                error=false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return  error;
    }
    public String changeTime(String time)
    {
        String timeString="";
        System.out.println("Number is "+time);
        int convertString=Integer.parseInt(time);
        switch (convertString)
        {
            case 1:
                timeString="7:00";
                break;
            case 2:
                timeString="7:50";
                break;
            case 3:
                timeString="8:50";
                break;
            case 4:
                timeString="9:40";
                break;
            case 5:
                timeString="10:40";
                break;
            case 7:
                timeString="12:30";
                break;
            case 8:
                timeString="13:20";
                break;
            case 9:
                timeString="14:20";
                break;
            case 10:
                timeString="15:10";
                break;
            case 11:
                timeString="16:10";
                break;
            case 12:
                timeString="17:50";
                break;
            case 13:
                timeString="6:00";
                break;
            default:
                timeString="7:40";
                break;
        }
        return timeString;
    }
}
