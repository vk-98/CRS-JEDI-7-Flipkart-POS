package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * @author JEDI-07
 * Professor Interface
 */
public interface ProfessorInterface {

    /**
     * method to add Grade in the database
     *
     * @param studentId unique Id for a student
     * @param courseId  unique Id to represent a course
     * @param grade     Grade assigned to student for a course
     * @return returns true if the grade is added successfully by professor
     */
    boolean addGrade(int studentId, int courseId, double grade);

    /**
     * Method to view all enrolled students in a particular course
     *
     * @param courseId unique Id to represent a course
     * @return List of Students
     */

    List<Student> getEnrolledStudents(int courseId);

    /**
     * method to view all selected course
     *
     * @return list of selected courses
     */
    List<Course> getSelectedCourses();

    /**
     * method to select the course to teach
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is selected successfully
     */
    boolean selectCourse(int courseId);

    /**
     * method to deselect the course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if deselection of course is successful
     */
    boolean deselectCourse(int courseId);


    /**
     * method to view all available courses
     *
     * @return list of courses.
     */
    List<Course> getAvailableCourses();

    /**
     * method to retrieve Professor Details
     */
    void getProfessor();

}
