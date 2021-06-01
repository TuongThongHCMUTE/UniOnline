package com.example.unionline.DAO;

import com.example.unionline.Common;
import com.example.unionline.DTO.Attendance;
import com.example.unionline.DTO.ClassModel1;
import com.example.unionline.DTO.Enrollment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnrollmentDAO {
    String path;
    DatabaseReference mDataBase;

    private static EnrollmentDAO instance;

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

}
