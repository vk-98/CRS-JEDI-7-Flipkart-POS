package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

/**
 * @author JEDI-07
 * Admin Client
 */
public interface ProfessorInterface {

    /**
     *Method to add Grade in the database
     *@param studentId
     *@param courseId
     * @param grade
     */

    void addGrade(int studentId, int courseId, double grade);

    /**
     * Method to view all enrolled students in a particular course
     * @param courseId
     */

    void viewEnrolledStudents(int courseId);

    /**
     * Method to view all selected course
     */

    void viewSelectedCourses();

    /**
     * method to select the course to teach
     * @param courseId
     * @return isCourseSelected
     */
    boolean selectCourse(int courseId);

    /**
     * method to deselect the course
     *
     * @param courseId
     * @return isCourseDeselected
     */
    boolean deselectCourse(int courseId);


    /**
     * method to view all available courses
     * @return list of courses.
     */
    List<Course> getAvailableCourses();

    /**
     * method to retrieve Professor Details
     */
    void getProfessor();

}
