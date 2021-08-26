package com.flipkart.bean;

public class User {
    private String userName;
    private String userEmailId;
    private String userPassword;
    private String role;
    private String id;
    private String phoneNo;

    public User(String name, String email, String password, String role, String phone) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPassword = userPassword;
        this.role = role;
        this.phoneNo = phoneNo;
    }

    public User(String userName, String userEmailId, String userPassword, String userPhoneNo) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPassword = userPassword;
        this.phoneNo = userPhoneNo;
    }

    public String getId() {
        return id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public User() {
    }

    public User(String userName, String userEmailId, String userPassword, String role, String phoneNo, String id) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPassword = userPassword;
        this.role = role;
        this.phoneNo = phoneNo;
        this.id = id;


    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
