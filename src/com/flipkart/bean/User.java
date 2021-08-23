package com.flipkart.bean;

public abstract class User {
    private String userName;
    private String userEmailId;
    private String userPassword;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    private String phoneNo;

    public User() {
    }

    public User(String userName, String userEmailId, String userPassword, String phoneNo) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPassword = userPassword;
        this.phoneNo = phoneNo;
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
