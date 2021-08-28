package com.flipkart.bean;

import com.flipkart.constants.Roles;

/**
 * @author JEDI-07
 * Student Bean
 */
public class Student extends User {
    private int studentId;
    private boolean isApproved;

    public Student() {
    }

    /**
     * Parameterized Constructor
     *
     * @param userName     Name of the User
     * @param userEmailId  EmailId of the User
     * @param userPassword Password of the User
     * @param phoneNo      PhoneNo of the User
     */
    public Student(String userName, String userEmailId, String userPassword, String phoneNo) {
        super(userName, userEmailId, userPassword, Roles.Student, phoneNo);
        this.isApproved = false;
    }

    /**
     * Getter for studentId
     *
     * @return studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Setter for StudentId
     *
     * @param studentId Unique Id of the Student
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter for isApproved
     *
     * @return isApproved
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Setter for isApproved Attribute
     *
     * @param approved Indicates Whether Student is approved or not
     */
    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
