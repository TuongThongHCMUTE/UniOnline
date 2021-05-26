package com.example.unionline.DTO;

public class Notification {
    private String id;
    private String title;
    private String content;
    private String image;
    private String sentTo;

    public Notification(String id, String title, String content, String image, String sentTo) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.sentTo = sentTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }
}
