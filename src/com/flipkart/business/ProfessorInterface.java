package com.flipkart.business;

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
     * Method to select the course to teach
     * @param courseId
     */

    void selectCourse(int courseId);

    /**
     * Method to deselect the course
     * @param courseId
     */

    void deselectCourse(int courseId);


    /**
     * Method to view all available courses
     */

    void viewAvailableCourses();

    /**
     * Method to retrieve Professor Details
     */

    void getProfessor();

}
