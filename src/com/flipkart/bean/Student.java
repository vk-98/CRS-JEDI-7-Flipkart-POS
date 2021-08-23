package com.flipkart.bean;

public class Student extends User{
    private String studentId;
    private boolean isApproved;
    private String branch;

    public Student() {
    }

    public Student(String userName, String userEmailId, String userPassword, String phoneNo, String studentId, boolean isApproved, String branch) {
        super(userName, userEmailId, userPassword, phoneNo);
        this.studentId = studentId;
        this.isApproved = isApproved;
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
