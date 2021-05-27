package com.example.unionline.DAO;

import com.example.unionline.DTO.Lesson;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public void changeStatusLesson(Lesson lesson, String semesterId) {
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child(path)
                .child(semesterId)
                .child(String.valueOf(lesson.getLessonId()))
                .child("status").setValue(!lesson.isStatus());
    }
}
