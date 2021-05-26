package com.example.unionline.DTO;

public class Score {
    private String classId;
    private String studentId;
    private String midScore;
    private String finalScore;

    public Score(String classId, String studentId, String midScore, String finalScore) {
        this.classId = classId;
        this.studentId = studentId;
        this.midScore = midScore;
        this.finalScore = finalScore;
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
