package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JEDI-07
 * Admin Dao Interface
 */
public interface AdminDaoInterface {
    /**
     * method for adding course into database
     *
     * @param courseName        Name of the course
     * @param courseDescription A brief description of course
     * @param courseFee         Fee assigned to a course
     * @return returns true if the course is added successfully
     */
    boolean addCourse(String courseName, String courseDescription, double courseFee);

    /**
     * method for removing course from the database
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is removed successfully
     */
    boolean removeCourse(int courseId);

    /**
     * method for getting all Pending admission requests
     *
     * @return List of students with pending request
     */
    List<Student> getPendingAdmissions();

    /**
     * method to approve a student by student id
     *
     * @param studentId unique Id for a student
     * @return returns true if student is approved successfully
     */
    boolean approveStudent(int studentId);

    /**
     * method for adding professor into the database
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
     * method for Viewing all the courses in the database
     *
     * @return List of courses
     */
    List<Course> viewCourses();

    /**
     * method for getting all the professors
     *
     * @return List of Professors
     */
    List<Professor> getProfessors();
}
