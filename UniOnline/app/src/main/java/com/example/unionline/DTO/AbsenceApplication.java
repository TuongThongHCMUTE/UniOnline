package com.example.unionline.DTO;

public class AbsenceApplication {
    private int id;
    private String classId;
    private String studentId;
    private String reason;
    private int state;

    public AbsenceApplication(){}

    public AbsenceApplication(int id, String classId, String studentId, String reason, int state) {
        this.id = id;
        this.classId = classId;
        this.studentId = studentId;
        this.reason = reason;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
