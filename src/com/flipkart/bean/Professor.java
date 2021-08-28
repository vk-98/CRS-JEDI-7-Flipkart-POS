package com.flipkart.bean;

/**
 * @author JEDI-07
 * Professor Bean
 */
public class Professor extends User {
    private int professorId;
    private String department;
    private String designation;

    public Professor() {
    }

    /**
     * Parameterized Constructor
     *
     * @param userName     Name of the User
     * @param userEmailId  EmailId of the User
     * @param userPassword Password of the User
     * @param role         Role of the User
     * @param phoneNo      Phone No of the User
     * @param department   Department of the User
     * @param designation  Designation of the User
     */
    public Professor(String userName, String userEmailId, String userPassword, String role, String phoneNo, String department, String designation) {
        super(userName, userEmailId, userPassword, role, phoneNo);
        this.department = department;
        this.designation = designation;
    }

    /**
     * Getter for ProfessorId
     *
     * @return professorId
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * Setter for ProfessorId
     *
     * @param professorId Unique Id of the Professor
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    /**
     * Getter for Department
     *
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Setter For department
     *
     * @param department department of the professor
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Getter for designation
     *
     * @return designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Setter for designation
     *
     * @param designation designation of the Professor
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
