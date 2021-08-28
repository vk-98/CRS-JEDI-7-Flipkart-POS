package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * @author JEDI-07
 * Admin Client
 */
public interface AdminInterface {
    /**
     * method for adding course into the catalogue
     *
     * @param courseName
     * @param courseDescription
     * @param courseFee
     * @return isCourseAdded
     */
    boolean addCourse(String courseName, String courseDescription, double courseFee);

    /**
     * method for removing course
     *
     * @param courseId
     * @return isCourseRemoved
     */
    boolean removeCourse(int courseId);

    /**
     * method for approving students admission request.
     *
     * @param studentId
     * @return
     */
    boolean approveStudentRequest(int studentId);

    /**
     * method for adding professor
     *
     * @param name
     * @param emailId
     * @param password
     * @param phoneNo
     * @param department
     * @param designation
     * @return isProfessorAdded
     */
    boolean addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation);

    /**
     * method for getting all admission requests
     *
     * @return List of students
     */
    List<Student> getAdmissionRequests();

    /**
     * method for geting all the professors
     *
     * @return List of Professors
     */
    List<Professor> getProfessors();
}
