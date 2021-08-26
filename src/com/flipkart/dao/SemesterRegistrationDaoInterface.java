package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.OptedCourse;
//import javafx.util.Pair;

/**
 * Interface for Semester Registration Dao Operations
 */
public interface SemesterRegistrationDaoInterface {

        /**
         * Method to add course in database
         * @param courseId
         * @param studentId
         * @param isPrimary
         * @return boolean indicating if the course is added successfully
         */
        public boolean addCourse(int courseId, int studentId, int isPrimary) ;

        /**
         * Drop Course selected by student
         * @param courseId
         * @param studentId
         */
        public boolean dropCourse(int courseId, int studentId) ;

        /**
         * Method to get the list of courses available from course catalog
         * @return list of Courses
         */
        public List<Course> viewCourses() ;

        /**
         * Method to View list of Registered Courses
         * @param studentId
         * @return list of Registered Courses
         */
        public List<OptedCourse> viewRegisteredCourses(int studentId) ;

        /**
         * Method for Student to submit opted courses for Semester
         * @param studentId
         */
        public boolean submitChoices(int studentId);

        /**
         * Method to get the number of Primary and Secondary courses registered by the student
         * @param studentId
         * @return list of number of registered Courses in Primary and Secondary
         */
        public List<Integer> getPrimarySecondaryCoursesCount(int studentId);

        /**
         * Method to get the CourseId for which registration is unsuccessful
         * @param studentId
         * @return CourseID
         */
        public int getCourseIdIfSeatNotAvailable(int studentId);


       // public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;

        /**
         * Method to retrieve fee for the selected courses from the database and calculate total fee
         * @param studentId
         * @return Fee Student has to pay
         * @throws SQLException
         */
        public double calculateFee(int studentId) throws SQLException;


       // public boolean seatAvailable(String courseCode) throws SQLException;


       // public int numOfRegisteredCourses(int studentId) throws SQLException;

        /**
         * Method checks if the student is registered for that course
         * @param courseId
         * @param studentId
         * @return Students registration status
         * @throws SQLException
         */
        public boolean isRegistered(String courseId, int studentId) throws SQLException;

        /**
         *  Method to get student registration status
         * @param studentId
         * @return Student's registration status
         * @throws SQLException
         */
        public boolean getRegistrationStatus(int studentId) throws SQLException;

        /**
         *  Method to get student Payment status
         * @param studentId
         * @return Student's Payment status
         * @throws SQLException
         */
        public boolean getPaymentStatus(int studentId) throws SQLException;

        /**
         *  Method to set student registration status
         * @param studentId
         * @param status
         * @throws SQLException
         */
        public boolean setRegistrationStatus(int studentId,int status) throws SQLException;

        /**
         *  Method to set student Payment status
         * @param studentId
         * @param status
         * @throws SQLException
         */
        public boolean setPaymentStatus(int studentId,int status) throws SQLException;



}
