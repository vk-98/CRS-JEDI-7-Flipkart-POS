package com.flipkart.bean;

/**
 * User Class
 */
public class User {
    private String userName;
    private String userEmailId;
    private String userPassword;
    private String role;
    private int id;
    private String phoneNo;

    public User() {
    }

    public User(String userName, String userEmailId, String userPassword, String role, String phoneNo) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPassword = userPassword;
        this.role = role;
        this.phoneNo = phoneNo;
    }


    /**
     * Method to get Id of the User
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set Id of the User
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get Role of the User
     */
    public String getRole() {
        return role;
    }

    /**
     * Method to set Role of the User
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method to get phone number of the User
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Method to set phone number of the User
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Method to get Name of the User
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Method to set Name of the User
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Method to get EmailID of the User
     */
    public String getUserEmailId() {
        return userEmailId;
    }

    /**
     * Method to set EmailId of the User
     */
    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    /**
     * Method to get Password of the User
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Method to set Password of the User
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
