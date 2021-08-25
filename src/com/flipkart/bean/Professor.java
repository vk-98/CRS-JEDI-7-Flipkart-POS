package com.flipkart.bean;

public class Professor extends User {
    private String professorId;
    private String department;
    private String designation;

    public Professor() {
    }

    public String getProfessorId() {
        return professorId;
    }

    public Professor(String userName, String userEmailId, String userPassword, String role, String phoneNo, String professorId, String department, String designation) {
        super(userName, userEmailId, userPassword, role, phoneNo);
        this.professorId = professorId;
        this.department = department;
        this.designation = designation;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
