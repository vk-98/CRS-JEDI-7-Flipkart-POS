package com.flipkart.business;

import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;

import java.sql.SQLException;

/**
 * @author JEDI-07
 * Admin Client
 */
public interface SemesterRegistrationInterface {

    boolean addCourse(int courseId, int isPrimary) ;

    boolean dropCourse(int courseId);

    void getRegisteredCourses();

    void getSelectedCourses();

    boolean submitCourseChoices() ;

    double getPendingFee();

    boolean payFee(double amount);
}
