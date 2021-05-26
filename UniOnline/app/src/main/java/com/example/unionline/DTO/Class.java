package com.example.unionline.DTO;

public class Class {

    private String classId;
    private String semesterId;
    private String teacherId;
    private String className;
    private Integer capacity;
    private String room;
    private String startTime;
    private String endTime;
    private String state;
    private boolean isActive;

    public Class() {
    }

    public Class(String classId, String semesterId, String teacherId, String className, Integer capacity, String room, String startTime, String endTime, String state, boolean isActive) {
        this.classId = classId;
        this.semesterId = semesterId;
        this.teacherId = teacherId;
        this.className = className;
        this.capacity = capacity;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
        this.isActive = isActive;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
