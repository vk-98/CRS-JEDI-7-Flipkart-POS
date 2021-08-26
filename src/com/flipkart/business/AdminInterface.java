package com.flipkart.business;

import com.flipkart.bean.Professor;

/**
 * Interface for Admin Operations
 */
public interface AdminInterface {

    /**
     * Method to add Course into the Course Catalog
     * @param courseName
     * @param courseDescription
     * @@param courseFee
     */
    void addCourse(String courseName, String courseDescription, double courseFee);

    /**
     * Method to remove Course from Course Catalog
     * @param courseId
     */
    void removeCourse(int courseId);

    /**
     *  Method to approve Student's Registration
     * 	@param studentId
     */
    void approveStudentRequest(int studentId);

    /**
     *  Method to add Professor into the DataBase
     * 	@param name
     * 	@param emailId
     * 	@param password
     * 	@param phoneNo
     * 	@param department
     * 	@param designation
     */
    void addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation);

    /**
     * Method to view all the pending Registration/Admission requests by Students
     */
    void listAdmissionRequests();

    /**
     * Method to view all the Professors in the DataBase
     */
    void viewProfessors();

}
