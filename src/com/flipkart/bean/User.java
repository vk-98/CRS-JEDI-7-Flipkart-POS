package com.flipkart.bean;

/**
 * @author JEDI-07
 * User Bean
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

    /**
     * Parameterized Constructor
     *
     * @param userName     Name of the User
     * @param userEmailId  EmailId of the User
     * @param userPassword Password of the User
     * @param role         Role of the User
     * @param phoneNo      Phone Number of the User
     */
    public User(String userName, String userEmailId, String userPassword, String role, String phoneNo) {
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPassword = userPassword;
        this.role = role;
        this.phoneNo = phoneNo;
    }

    /**
     * Getter fot Id
     *
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for Id
     *
     * @param id Unique Id of the User
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for Role
     *
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for Role
     *
     * @param role Role of the User
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter for Phone No
     *
     * @return phone Number
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Setter For Phone No
     *
     * @param phoneNo Phone no of the user
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Getter for userName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for UserName
     *
     * @param userName Name of the User
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for User EmailId
     *
     * @return userEmailId
     */
    public String getUserEmailId() {
        return userEmailId;
    }

    /**
     * Setter for UserEmailId
     *
     * @param userEmailId EmailId of the User
     */
    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    /**
     * Getter for User Password
     *
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Setter for UserPassword
     *
     * @param userPassword Password of the User
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
