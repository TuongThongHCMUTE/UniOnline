package com.example.unionline.DTO;

import java.io.Serializable;

public class Enrollment implements Serializable {
    private String id;
    private String classId;
    private String className;
    private String classRoom;
    private String fullDate;
    private String studentId;
    private String studentName;
    private String midScore;
    private String finalScore;

    public Enrollment(){};

    public Enrollment(String classId, String className, String classRoom, String fullDate, String studentId, String studentName, String midScore, String finalScore) {
        this.classId = classId;
        this.className = className;
        this.classRoom = classRoom;
        this.fullDate = fullDate;
        this.studentId = studentId;
        this.studentName = studentName;
        this.midScore = midScore;
        this.finalScore = finalScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMidScore() {
        return midScore;
    }

    public void setMidScore(String midScore) {
        this.midScore = midScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }
}
