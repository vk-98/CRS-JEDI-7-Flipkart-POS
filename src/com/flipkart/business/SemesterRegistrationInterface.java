package com.flipkart.business;

import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;

import java.sql.SQLException;

/**
 * Interface for Semester Registration operations
 */
public interface SemesterRegistrationInterface {

    /**
     * Method to add Primary Course for a Student
     * @param studentId
     * @param  courseId : ID of the course to be added
     */
    boolean addPrimaryCourse(int studentId, int courseId);

    /**
     * Method to add Secondary Course for a Student
     * @param studentId
     * @param  courseId : ID of the course to be added
     */
    boolean addSecondaryCourse(int studentId, int courseId);

    /**
     * Method to drop a Course for a Student
     * @param studentId
     * @param  courseId : ID of the course to be droped
     */
    boolean dropCourse(int studentId, int courseId);

    /**
     * Method to view all the registered courses for a Student
     * @param studentId
     */
    void viewRegisteredCourses(int studentId);

    /**
     * Method to calculate the total fee required to register for the semester
     * @param studentId
     * @return Semester Fee
     */
    double calculateFee(int studentId) throws SQLException;

    /**
     * Method to submit the opted courses for the semester
     * @param studentId
     * @throws NoRegisteredCourseException
     * @throws CourseCountException
     * @throws SeatNotAvailableException
     * @throws SQLException
     */
    boolean submitCourseChoices(int studentId) throws NoRegisteredCourseException, CourseCountException, SeatNotAvailableException, SQLException;

    /**
     * Method to check if Student is registered for the Semester
     * @param studentId
     * @throws SQLException
     */
    boolean getRegistrationStatus(int studentId) throws SQLException;

    /**
     * Method to check if Student paid for the Semester
     * @param studentId
     * @throws SQLException
     */
    boolean getPaymentStatus(int studentId) throws SQLException;

    /**
     * Method to set the Registration status of the Student
     * @param studentId
     * @param status
     * @throws SQLException
     */
    void setRegistrationStatus(int studentId, boolean status) throws SQLException;

    /**
     * Method to set the Payment status of the Student for a Semester
     * @param studentId
     * @param status
     * @throws SQLException
     */
    void setPaymentStatus(int studentId, boolean status) throws SQLException;
}
