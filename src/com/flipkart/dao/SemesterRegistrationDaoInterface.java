package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.OptedCourse;

/**
 * @author JEDI-07
 * Admin Client
 */
public interface SemesterRegistrationDaoInterface {

    public boolean registerForSemester();

    public int getSemesterId();

    public int getCourseCount(int isPrimary);

    public boolean checkAvailability(int courseId);

    public boolean getRegistrationStatus();

    public boolean addCourse(int courseId, int semesterId, int isPrimary);

    public boolean dropCourse(int courseId);

    public List<Course> viewCourses();

    public List<OptedCourse> getRegisteredCourses();

    public List<OptedCourse> getSelectedCourses();

    public List<Integer> getPrimarySecondaryCoursesCount(int studentId);

    public int getCourseIdIfSeatNotAvailable(int studentId);

    public double calculateFee(int studentId) throws SQLException;

    public boolean isCourseAlreadyRegistered(int courseId) ;

    public boolean getRegistrationStatus(int studentId) throws SQLException;

    public boolean getPaymentStatus();

    public boolean setRegistrationStatus(int studentId, int status) throws SQLException;

    public boolean setPaymentStatus(int studentId, int status) throws SQLException;

    public boolean allotCourse(int courseId);

    public boolean submitRegistration(double courseFee);

    public boolean updateStudentCount(int courseId);

    public double getPendingFee();

    public boolean payFee(double amount);

}
