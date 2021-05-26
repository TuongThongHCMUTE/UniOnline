package com.example.unionline.DTO;

public class Attendance {
    private String classId;
    private String lessonId;
    private String studentId;
    private String state;

    public Attendance(){}

    public Attendance(String classId, String lessonId, String studentId, String state) {
        this.classId = classId;
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.state = state;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
