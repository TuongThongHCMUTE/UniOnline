package com.example.unionline.DTO;

public class Lesson {
    private String lessonId;
    private String classId;
    private String name;
    private String description;
    private boolean status;

    public Lesson(){}

    public Lesson(String lessonId, String classId, String name, String description, boolean status) {
        this.lessonId = lessonId;
        this.classId = classId;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
