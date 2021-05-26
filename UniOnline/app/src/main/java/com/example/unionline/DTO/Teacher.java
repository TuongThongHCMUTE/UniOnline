package com.example.unionline.DTO;

public class Teacher {
    private String userId;
    private String password;
    private String gender;
    private String major;
    private String name;
    private String email;
    private String phone;
    private int image;
    private boolean isActive;
    private String resetPasswordCode;

    public Teacher() {
    }

    public Teacher(String userId, String password, String gender, String major, String name, String email, String phone, int image, boolean isActive, String resetPasswordCode) {
        this.userId = userId;
        this.password = password;
        this.gender = gender;
        this.major = major;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.isActive = isActive;
        this.resetPasswordCode = resetPasswordCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }
}
