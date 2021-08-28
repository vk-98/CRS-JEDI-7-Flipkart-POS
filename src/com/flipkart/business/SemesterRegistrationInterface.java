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
     * @param courseId
     * @param isPrimary
     * @return isCourseAdded
     */
    boolean addCourse(int courseId, int isPrimary);

    /**
     * method fro dropping course
     *
     * @param courseId
     * @return isCourseDropped
     */
    boolean dropCourse(int courseId);

    /**
     * method for getting registered courses
     *
     * @return list of opted courses
     */
    List<OptedCourse> getRegisteredCourses();

    /**
     * method for getting selected courses
     *
     * @return list of optedcourses
     */
    List<OptedCourse> getSelectedCourses();

    /**
     * method for submitting course choices
     *
     * @return isChoiceSubmitted
     */
    boolean submitCourseChoices();

    /**
     * method for getting the pending fee.
     *
     * @return pendingFee
     */
    double getPendingFee();

    /**
     * method for paying fee
     *
     * @param amount
     * @return isFeePayementDone
     */
    boolean payFee(double amount);
}
