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
     * @param studentId
     * @param courseId
     * @param grade
     * @return graded
     */
    boolean addGrade(int studentId, int courseId, double grade);

    /**
     * Method to view all enrolled students in a particular course
     * @param courseId
     * @return List of courses
     */

    List<Student> getEnrolledStudents(int courseId);

    /**
     * method to view all selected course
     * @return list of selected courses
     */
    List<Course> getSelectedCourses();

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
