package com.flipkart.business;

import com.flipkart.bean.OptedCourse;
import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author JEDI-07
 * Semester Resgistration Interface
 */
public interface SemesterRegistrationInterface {
    /**
     * method for adding course for the logged in user
     *
     * @param courseId  unique Id to represent a course
     * @param isPrimary isPrimary Indicates if the course is primary or not
     * @return returns true if the course is added successfully
     */
    boolean addCourse(int courseId, int isPrimary);

    /**
     * method for dropping course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is dropped successfully
     */
    boolean dropCourse(int courseId);

    /**
     * method for getting registered courses
     *
     * @return list of registered courses
     */
    List<OptedCourse> getRegisteredCourses();

    /**
     * method for getting selected courses
     *
     * @return list of selected courses
     */
    List<OptedCourse> getSelectedCourses();

    /**
     * method for submitting course choices
     *
     * @return returns true if course choices are submitted successfully
     */
    boolean submitCourseChoices();

    /**
     * method for getting the pending fee.
     *
     * @return pending Fee
     */
    double getPendingFee();

    /**
     * method for paying fee
     *
     * @param amount Amount to be paid
     * @return returns true if payment is successful
     */
    boolean payFee(double amount);
}
