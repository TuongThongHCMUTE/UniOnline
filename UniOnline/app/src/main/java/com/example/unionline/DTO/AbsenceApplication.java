package com.example.unionline.DTO;

public class AbsenceApplication {
    private String classId;
    private String studentId;
    private String reason;
    private String state;

    public AbsenceApplication(String classId, String studentId, String reason, String state) {
        this.classId = classId;
        this.studentId = studentId;
        this.reason = reason;
        this.state = state;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
