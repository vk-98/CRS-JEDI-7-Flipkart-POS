package com.flipkart.bean;

import com.flipkart.constants.Roles;

/**
 * Student Class
 */
public class Student extends User {
    private String studentId;
    private boolean isApproved;


    public Student() {
    }

    public Student(String userName, String userEmailId, String userPassword, String phoneNo) {
        super(userName, userEmailId, userPassword, phoneNo, Roles.Student);
        this.isApproved = false;
    }


    /**
     * Method to check if student is registered for the CRS
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Method to make a student register for the CRS
     */
    public void setApproved(boolean approved) {
        isApproved = approved;
    }

}
