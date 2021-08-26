package com.flipkart.business;

import com.flipkart.exception.CourseCountException;
import com.flipkart.exception.NoRegisteredCourseException;
import com.flipkart.exception.SeatNotAvailableException;

import java.sql.SQLException;

public interface SemesterRegistrationInterface {

    boolean addPrimaryCourse(int studentId, int courseId);

    boolean addSecondaryCourse(int studentId, int courseId);

    boolean dropCourse(int studentId, int courseId);

    void viewRegisteredCourses(int studentId);

    double calculateFee(int studentId) throws SQLException;

    boolean submitCourseChoices(int studentId) throws NoRegisteredCourseException, CourseCountException, SeatNotAvailableException, SQLException;

    boolean getRegistrationStatus(int studentId) throws SQLException;
    boolean getPaymentStatus(int studentId) throws SQLException;

    void setRegistrationStatus(int studentId, boolean status) throws SQLException;
    void setPaymentStatus(int studentId, boolean status) throws SQLException;
}
