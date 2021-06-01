package com.example.unionline.DAO;

import com.example.unionline.Common;
import com.example.unionline.DTO.Attendance;
import com.example.unionline.DTO.ClassModel1;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AttendanceDAO {
    String path;
    DatabaseReference mDataBase;

    private static AttendanceDAO instance;

    public static AttendanceDAO getInstance() {
        if (instance == null) {
            instance = new AttendanceDAO();
        }
        return instance;
    }

    public static void setInstance(AttendanceDAO instance) {
        AttendanceDAO.instance = instance;
    }

    public AttendanceDAO() { path = "Attendances"; }

    public void changeAttendanceState(Attendance attendance) {
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child(path)
                .child(Common.semester.getSemesterId())
                .child(String.valueOf(attendance.getId()))
                .child("state").setValue(attendance.getState());
    }
    public boolean deleteAntendence(Attendance attendance, ClassModel1 classModel1) {
        try {
            mDataBase = FirebaseDatabase.getInstance().getReference();
            mDataBase.child(path).child(classModel1.getSemesterId()).child(attendance.getId()).removeValue();
            return true;
        } catch (Error error){
            return false;
        }
    }
}
