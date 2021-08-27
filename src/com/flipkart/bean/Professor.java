package com.flipkart.bean;
// Professor class
public class Professor extends User {
    private int professorId;
    private String department;
    private String designation;

    public Professor() {
    }

    public Professor(String userName, String userEmailId, String userPassword, String role, String phoneNo, String department, String designation) {
        super(userName, userEmailId, userPassword, role, phoneNo);
        this.department = department;
        this.designation = designation;
    }
    //Method to retrieve Professor ID

    public int getProfessorId()
    {
        return professorId;
    }
    //Method to set Professor ID

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
    //Method to retrieve Department Name
    public String getDepartment() {
        return department;
    }
    //Method to set Department Name
    public void setDepartment(String department) {
        this.department = department;
    }

    //Method to retrieve Designation
    public String getDesignation() {
        return designation;
    }
    //Method to set Designation
    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
