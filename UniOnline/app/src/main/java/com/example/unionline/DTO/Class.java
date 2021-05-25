package com.example.unionline.DTO;

import java.util.Date;

public class Class {

    private String id;
    private String name;
    private String day;
    private Date startDate;
    private Date endDate;
    private int startClass;
    private int endClass;
    private String teacherId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStartClass() {
        return startClass;
    }

    public void setStartClass(int startClass) {
        this.startClass = startClass;
    }

    public int getEndClass() {
        return endClass;
    }

    public void setEndClass(int endClass) {
        this.endClass = endClass;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     *
     */
    public Class() {

    }

    /**
     * Class Constructor
     * @param id: class id
     * @param name: class name
     * @param day: class in which day of week?
     * @param startDate: date beginning class
     * @param endDate: date ending class
     * @param startClass: first class start at
     * @param endClass: last class end at
     * @param teacherId: id of teacher who teaches this class
     */
    public Class(String id, String name, String day, Date startDate, Date endDate, int startClass, int endClass, String teacherId) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startClass = startClass;
        this.endClass = endClass;
        this.teacherId = teacherId;
    }


}
