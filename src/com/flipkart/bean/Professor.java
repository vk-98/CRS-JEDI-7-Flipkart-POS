package com.flipkart.bean;

public class Professor extends User {
    private int professorId;
    private String department;
    private String designation;

    public Professor() {
    }

    public int getProfessorId() {
        return professorId;
    }

    public Professor(String userName, String userEmailId, String userPassword, String role, String phoneNo, String department, String designation) {
        super(userName, userEmailId, userPassword, role, phoneNo);
        this.department = department;
        this.designation = designation;
    }

    public void setProfessorId(int professorId) {
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
