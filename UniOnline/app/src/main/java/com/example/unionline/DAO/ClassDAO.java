package com.example.unionline.DAO;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.unionline.DTO.Class;
import com.google.firebase.database.FirebaseDatabase;

public class ClassDAO implements Dao<Class> {

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    @Override
    public Optional<Class> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Class> getAll() {
        return null;
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
