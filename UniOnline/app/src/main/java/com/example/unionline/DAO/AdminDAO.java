package com.example.unionline.DAO;

import com.example.unionline.DTO.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDAO {
    String path_student, path_admin, path_teacher, path_parent, path_qlk;
    DatabaseReference mDatabase;

    private static AdminDAO instance;

    public static AdminDAO getInstance() {
        if (instance==null)
            instance = new AdminDAO();
        return instance;
    }

    public static void setInstance(AdminDAO instance) { AdminDAO.instance = instance;}

    public AdminDAO() {
        path_admin = "Users/Admin";
        path_parent = "Users/Parent";
        path_qlk = "Users/Qlkhoa";
        path_student = "Users/Student";
        path_teacher = "Users/Teacher";
    }
    public void setStudentValue(User user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(path_student).child(String.valueOf(user.getName())).setValue(user);
    }
    public void setTeacherValue(User user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(path_teacher).child(String.valueOf(user.getName())).setValue(user);
    }
    public void setAdminValue(User user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(path_admin).child(String.valueOf(user.getName())).setValue(user);
    }
    public void setParentValue(User user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(path_parent).child(String.valueOf(user.getName())).setValue(user);
    }
    public void setQlkhoaValue(User user) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(path_qlk).child(String.valueOf(user.getName())).setValue(user);
    }
}
