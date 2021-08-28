package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.OptedCourse;

/**
 * @author JEDI-07
 * SemesterRegistration for DAO Interface
 */
public interface SemesterRegistrationDaoInterface {

    /**
     * method for Student registration for Semester
     *
     * @return returns true if student successfully registers
     */
    public boolean registerForSemester();

    /**
     * method to get Semester Id
     *
     * @return returns the semesterId
     */
    public int getSemesterId();

    /**
     * method to get number of courses selected by student
     *
     * @param isPrimary Indicates if the course is primary or not
     * @return returns the number of courses selected by student
     */
    public int getCourseCount(int isPrimary);

    /**
     * method to check if a course is available for the semster
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is available for semester
     */

    public boolean checkAvailability(int courseId);

    /**
     * method to get the Registration status of Semester by student
     *
     * @return returns true if he has successfully registerd for the semester
     */
    public boolean getRegistrationStatus();

    /**
     * Method to add course
     *
     * @param courseId   unique Id to represent a course
     * @param semesterId Id of the Current Semester
     * @param isPrimary  Indicates if the course is primary or not
     * @return returns true if the course is added successfully
     */
    public boolean addCourse(int courseId, int semesterId, int isPrimary);

    /**
     * Method to Drop Course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is dropped successfully
     */
    public boolean dropCourse(int courseId);

    /**
     * Method to View all the courses
     *
     * @return List of courses
     */
    public List<Course> viewCourses();

    /**
     * Method to Get List of Registered  courses of the Student for a semester
     *
     * @return List of Registered Courses
     */

    public List<OptedCourse> getRegisteredCourses();

    /**
     * Method to Get List of Selected  courses of the Student for a semester
     *
     * @return List of Selected Courses
     */
    public List<OptedCourse> getSelectedCourses();

    /**
     * Method to get count of Primary and Secondary courses of a student for a Semester
     *
     * @param studentId unique Id for a student
     * @return List of count of Primary and Secondary courses
     */
    public List<Integer> getPrimarySecondaryCoursesCount(int studentId);

    /**
     * Method to get courseId if seat is not available
     *
     * @param studentId unique Id for a student
     * @return Returns CourseId if seat is not available
     */
    public int getCourseIdIfSeatNotAvailable(int studentId);

    /**
     * Method to calculate fee for the Semester to a student
     *
     * @param studentId unique Id for a student
     * @return returns Calculated Semester fee
     * @throws SQLException
     */
    public double calculateFee(int studentId) throws SQLException;

    /**
     * Method to find if course is already registered
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is already registered or not
     */
    public boolean isCourseAlreadyRegistered(int courseId);

    /**
     * Method to get registration status of a student for a semester
     *
     * @param studentId unique Id for a student
     * @return returns true if the student is registered for the semester
     * @throws SQLException
     */
    public boolean getRegistrationStatus(int studentId) throws SQLException;

    /**
     * Method to Get Payment Status of student
     *
     * @return returns true if Payment is done successfully by the student
     */
    public boolean getPaymentStatus();

    /**
     * Method to Set Registration status of the student
     *
     * @param studentId unique Id for a student
     * @param status    the status to be set
     * @return returns true if registration status is set successfully
     * @throws SQLException
     */
    public boolean setRegistrationStatus(int studentId, int status) throws SQLException;

    /**
     * Method to Set Payment status of the student
     *
     * @param studentId unique Id for a student
     * @param status    the status to be set
     * @return returns true if payment status is set successfully
     * @throws SQLException
     */
    public boolean setPaymentStatus(int studentId, int status) throws SQLException;

    /**
     * Method to allot course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is allotted successfully
     */
    public boolean allotCourse(int courseId);

    /**
     * Method to Submit Registration
     *
     * @param courseFee Fee assigned to a course
     * @return returns true if registration submitted successfully
     */
    public boolean submitRegistration(double courseFee);

    /**
     * Method to Update Student count for a course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if Student count is updated
     */
    public boolean updateStudentCount(int courseId);

    /**
     * Method to get Pending fee
     *
     * @return returns the pending fee
     */
    public double getPendingFee();

    /**
     * Method to Pay fee
     *
     * @param amount Amount to be paid
     * @return returns true if payment is successful
     */
    public boolean payFee(double amount);

}
