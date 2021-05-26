package com.example.unionline.DTO;

import com.example.unionline.DAO.Dao;

public class Notification {
    private int id;
    private String title;
    private String content;
    private int image;
    private String sentTo;
    private String createDate;

    public Notification(){}

    public Notification(int id, String title, String content, int image, String sentTo, String createDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.sentTo = sentTo;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
