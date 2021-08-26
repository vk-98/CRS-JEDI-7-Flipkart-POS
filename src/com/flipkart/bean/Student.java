package com.flipkart.bean;

import com.flipkart.constants.Roles;

public class Student extends User {
    private String studentId;
    private boolean isApproved;

    public Student(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) {
        super(studentName, studentEmailId, studentPassword, studentPhoneNo);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }



    public Student() {
    }

    public Student(String studentId, String userName, String userEmailId, String userPassword, String phoneNo, String role, String userId) {
        super(userName, userEmailId, userPassword, phoneNo, Roles.Student, userId);
        this.studentId = studentId;
        this.isApproved = true;

    }



    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }


}
