package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;

public interface SemesterRegistrationDaoInterface {




        public boolean addCourse(int courseId, int studentId, int isPrimary) ;



        public boolean dropCourse(int courseId, int studentId) ;

        public List<Course> viewCourses() ;


        public List<Course> viewRegisteredCourses(int studentId) ;

        public boolean submitChoices(int studentId);


       // public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;


        public double calculateFee(int studentId) throws SQLException;


       // public boolean seatAvailable(String courseCode) throws SQLException;


       // public int numOfRegisteredCourses(int studentId) throws SQLException;


        public boolean isRegistered(String courseCode, int studentId) throws SQLException;


        public boolean getRegistrationStatus(int studentId) throws SQLException;


        public void setRegistrationStatus(int studentId) throws SQLException;



}
