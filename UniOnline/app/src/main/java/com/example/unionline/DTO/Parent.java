package com.example.unionline.DTO;

public class Parent {
    private String userId;
    private String password;
    private String gender;
    private String name;
    private String phone;
    private String email;
    private String address;
    private boolean isActive;
    private String resetPasswordCode;

    public Parent() {
    }

    public Parent(String userId, String password, String gender, String name, String phone, String email, String address, boolean isActive, String resetPasswordCode) {
        this.userId = userId;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
