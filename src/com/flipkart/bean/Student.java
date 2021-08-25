package com.flipkart.bean;

import com.flipkart.constants.Roles;

public class Student extends User {
    private String studentId;
    private boolean isApproved;
    private String branch;
    static int nextId = 1;

    public Student() {
    }

    public Student(String userName, String userEmailId, String userPassword, String phoneNo, String branch) {
        super(userName, userEmailId, userPassword, phoneNo, Roles.Student);
        this.studentId = String.valueOf(nextId);
        nextId++;
        this.isApproved = true;
        this.branch = branch;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
