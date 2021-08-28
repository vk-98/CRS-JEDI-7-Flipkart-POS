package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * @author JEDI-07
 * Admin Interface
 */
public interface AdminInterface {
    /**
     * method for adding course into the catalogue
     *
     * @param courseName        Name of the course
     * @param courseDescription A brief description of course
     * @param courseFee         Fee assigned to a course
     * @return returns true if the course is successfully added
     */
    boolean addCourse(String courseName, String courseDescription, double courseFee);

    /**
     * method for removing course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is successfully removed
     */
    boolean removeCourse(int courseId);

    /**
     * method for approving students admission request.
     *
     * @param studentId unique Id for a student
     * @return returns true if the student's request is approved successfully
     */
    boolean approveStudentRequest(int studentId);

    /**
     * method for adding professor
     *
     * @param name        name of the Professor
     * @param emailId     emailId of the Professor
     * @param password    password for the Professor
     * @param phoneNo     Phone Number of the Professor
     * @param department  Department of the Professor
     * @param designation Designation of the Professor
     * @return returns true if Professor is added successfully
     */
    boolean addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation);

    /**
     * method for getting all admission requests
     *
     * @return List of Students who made Admission Request
     */
    List<Student> getAdmissionRequests();

    /**
     * method for getting all the professors
     *
     * @return List of Professors
     */
    List<Professor> getProfessors();
}
