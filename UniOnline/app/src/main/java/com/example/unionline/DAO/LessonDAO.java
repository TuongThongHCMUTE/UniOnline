package com.example.unionline.DAO;

import androidx.annotation.NonNull;

import com.example.unionline.Common;
import com.example.unionline.DTO.Class;
import com.example.unionline.DTO.ClassModel1;
import com.example.unionline.DTO.Lesson;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LessonDAO {
    String path;
    DatabaseReference mDataBase;

    private static LessonDAO instance;

    public static LessonDAO getInstance() {
        if (instance == null) {
            instance = new LessonDAO();
        }
        return instance;
    }

    public static void setInstance(LessonDAO instance) {
        LessonDAO.instance = instance;
    }

    public LessonDAO() { path = "Lessons"; }

    public void update(Lesson lesson) {
        mDataBase = FirebaseDatabase.getInstance().getReference(path);
        mDataBase.child(Common.semester.getSemesterId())
                .child(lesson.getLessonId())
                .setValue(lesson);
    }

    public void changeStatusLesson(Lesson lesson, String semesterId) {
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child(path)
                .child(semesterId)
                .child(String.valueOf(lesson.getLessonId()))
                .child("status").setValue(!lesson.isStatus());
    }

    public List<Lesson> getAllLessonByClassModel(ClassModel1 classModel1)
    {
        List<Lesson> lessons=new ArrayList<>();
        mDataBase = FirebaseDatabase.getInstance().getReference("Lessons").child(classModel1.getSemesterId());
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lessons.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Lesson lesson= dataSnapshot.getValue(Lesson.class);
                    if(lesson.getClassId().equals(classModel1.getClassId()))
                        lessons.add(lesson);

                }
                System.out.println("List lesson"+lessons.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return lessons;
    }

    public List<Lesson> getAllLessonByClass(Class classObject)
    {
        List<Lesson> lessons=new ArrayList<>();
        mDataBase = FirebaseDatabase.getInstance().getReference("Lessons").child(classObject.getSemesterId());
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lessons.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Lesson lesson= dataSnapshot.getValue(Lesson.class);
                    if(lesson.getClassId().equals(classObject.getClassId()))
                        lessons.add(lesson);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return lessons;
    }
}
