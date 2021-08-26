package com.flipkart.bean;

import com.flipkart.constants.Roles;

public class Student extends User {
    private String studentId;
    private boolean isApproved;


    public Student() {
    }



    /**
     * Parameterized Constructor
     * @param userName: name of the user
     * @param userEmailId: email id of the user
     * @param userPassword: user password
     * @param phoneNo: user phone number
     */
    public Student(String userName, String userEmailId, String userPassword, String phoneNo) {
        super(userName, userEmailId, userPassword, phoneNo, Roles.Student);
        this.isApproved = false;
    }


    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


}
