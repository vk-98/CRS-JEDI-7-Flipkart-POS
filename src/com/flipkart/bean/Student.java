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

    public Student(String userName, String userEmailId, String userPassword, String phoneNo) {
        super(userName, userEmailId, userPassword, Roles.Student, phoneNo);
        this.isApproved = false;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
