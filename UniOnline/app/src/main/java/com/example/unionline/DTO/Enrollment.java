package com.example.unionline.DTO;

public class Enrollment {
    private String classId;
    private String studentId;
    private String studentName;

    public Enrollment(String classId, String studentId, String studentName) {
        this.classId = classId;
        this.studentId = studentId;
        this.studentName = studentName;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
