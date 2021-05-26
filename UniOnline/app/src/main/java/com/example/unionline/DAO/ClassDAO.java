package com.example.unionline.DAO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.unionline.DTO.Class;
import com.google.firebase.database.FirebaseDatabase;

public class ClassDAO implements Dao<Class> {

    static DatabaseReference mData;
    static List<Class> classes = new ArrayList<>();

    public ClassDAO() {
        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("Class").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Class aClass = snapshot.getValue(Class.class);
                classes.add(aClass);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public Optional<Class> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Class> getAll() {
        return classes;
    }

    @Override
    public void save(Class aClass) {
        mData.child("Class").push().setValue(aClass);
    }

    @Override
    public void update(Class aClass, String[] params) {

    }

    @Override
    public void delete(Class aClass) {

    }
}
