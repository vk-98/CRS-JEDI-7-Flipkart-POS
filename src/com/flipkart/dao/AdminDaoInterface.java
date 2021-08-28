package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;

import java.util.List;

/**
 * @author JEDI-07
 * Admin Dao Interface
 */
public interface AdminDaoInterface {
    /**
     * method for adding course into database
     * @param courseName
     * @param courseDescription
     * @param courseFee
     * @return isCourseCreated
     */
    boolean addCourse(String courseName, String courseDescription, double courseFee);

    /**
     * method for removing course from the database
     * @param courseId
     * @return courseRemoved
     */
    boolean removeCourse(int courseId);

    /**
     * method for getting all admission requests
     * @return List of students
     */
    List<Student> getPendingAdmissions();

    /**
     * method to approve a student by student id
     * @param studentId
     * @return
     */
    boolean approveStudent(int studentId);

    /**
     * method for adding professor into the database
     * @param name
     * @param emailId
     * @param password
     * @param phoneNo
     * @param department
     * @param designation
     * @return IsProfessorAdded
     */
    boolean addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation);

    List<Course> viewCourses();

    /**
     * method for geting all the professors
     * @return List of Professors
     */
    List<Professor> getProfessors();
}
