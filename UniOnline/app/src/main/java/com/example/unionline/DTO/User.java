package com.example.unionline.DTO;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private boolean isActive;
    private String resetPasswordCode;

    public User() {

    }

    public User(String userId, String password, String name, String email, String phone, boolean isActive, String resetPasswordCode) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
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
