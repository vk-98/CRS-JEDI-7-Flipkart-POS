package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.OptedCourse;

public interface SemesterRegistrationDaoInterface {




        public boolean addCourse(int courseId, int studentId, int isPrimary) ;



        public boolean dropCourse(int courseId, int studentId) ;

        public List<Course> viewCourses() ;


        public List<OptedCourse> viewRegisteredCourses(int studentId) ;

        public boolean submitChoices(int studentId);

        public List<Integer> getPrimarySecondaryCoursesCount(int studentId);

        public int getCourseIdIfSeatNotAvailable(int studentId);


        // public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;


        public double calculateFee(int studentId) throws SQLException;


        // public boolean seatAvailable(String courseCode) throws SQLException;


        // public int numOfRegisteredCourses(int studentId) throws SQLException;


        public boolean isRegistered(int courseId, int studentId) throws SQLException;


        public boolean getRegistrationStatus(int studentId) throws SQLException;
        public boolean getPaymentStatus(int studentId) throws SQLException;

        public boolean setRegistrationStatus(int studentId,int status) throws SQLException;
        public boolean setPaymentStatus(int studentId,int status) throws SQLException;



}
