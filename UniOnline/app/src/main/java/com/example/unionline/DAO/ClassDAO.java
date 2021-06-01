package com.example.unionline.DAO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.ClassModel1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ClassDAO {
    String path;
    DatabaseReference mDataBase;

    private static ClassDAO instance;

    public static ClassDAO getInstance() {
        if (instance == null) {
            instance = new ClassDAO();
        }
        return instance;
    }

    public static void setInstance(ClassDAO instance) {
        ClassDAO.instance = instance;
    }

    public ClassDAO() {
        path = "Classes";
    }

    public Class getClassById(String id) {
        final Class[] aClass = {new Class()};

        mDataBase = FirebaseDatabase.getInstance().getReference(path).child(id);
        mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, String previousChildName) {
                if (snapshot.getKey() == id) {
                    aClass[0] = snapshot.getValue(Class.class);
                    return;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return aClass[0];
    }
    public void setValude(ClassModel1 classModel1)
    {
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("Classes").child(classModel1.getSemesterId()).child(classModel1.getClassId()).setValue(classModel1);
    }
    public boolean deleteClass(ClassModel1 classModel1) {
        try {
            mDataBase = FirebaseDatabase.getInstance().getReference();
            mDataBase.child(path).child(classModel1.getSemesterId()).child(classModel1.getClassId()).removeValue();
            return true;
        } catch (Error error){
            return false;
        }
    }
}
