package com.example.unionline.DTO;

public class Score {
    private String id;
    private String classId;
    private String studentId;
    private String studentName;
    private Double midScore;
    private Double finalScore;

    public Score(String classId, String studentId, String studentName, Double midScore, Double finalScore) {
        this.classId = classId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getMidScore() {
        return midScore;
    }

    public void setMidScore(Double midScore) {
        this.midScore = midScore;
    }

    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
