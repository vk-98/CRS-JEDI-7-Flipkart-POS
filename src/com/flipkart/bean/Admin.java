package com.flipkart.bean;

/**
 * Admin Class
 */
public class Admin extends User {
    private int id;

    public Admin() {
    }

    public Admin(String userName, String userEmailId, String userPassword, String role, String phoneNo) {
        super(userName, userEmailId, userPassword, role, phoneNo);
    }

    /**
     * Method to retrieve AdminID
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set AdminId
     */
    public void setAId(int adminId) {
        this.id = id;
    }
}
