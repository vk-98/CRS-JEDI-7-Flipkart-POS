package com.flipkart.business;

import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;

import java.sql.SQLException;

public interface SemesterRegistrationInterface {

    boolean addPrimaryCourse(int studentId, int courseId) throws SQLException;

    boolean addSecondaryCourse(int studentId, int courseId) throws SQLException;

    boolean dropCourse(int studentId, int courseId);

    void viewRegisteredCourses(int studentId);

    double calculateFee(int studentId) throws SQLException;

    boolean submitCourseChoices(int studentId) throws NoRegisteredCourseException, CourseCountException, SeatNotAvailableException, SQLException;

    boolean getRegistrationStatus(int studentId) throws SQLException;
    boolean getPaymentStatus(int studentId) throws SQLException;

    void setRegistrationStatus(int studentId, boolean status) throws SQLException;
    void setPaymentStatus(int studentId, boolean status) throws SQLException;
}
