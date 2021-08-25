package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SemesterRegistrationDaoOperation implements SemesterRegistrationDaoInterface {
    static Connection con = DBUtil.getConnection();
    NotificationDaoInterface notificationDaoInterface= new NotificationDaoOperation();


    @Override
    public boolean addCourse(int courseId, int studentId, int isPrimary) {

        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_SEM_REGISTRATION_ID);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            int semRegId = -1;
            if (rs.next()) {
                semRegId = rs.getInt("id");
            } else {
                ps = con.prepareStatement(SqlQueries.ADD_SEMESTER_REGISTRATION);
                ps.setInt(1, studentId);
                ps.setInt(2, 0);
                ps.setInt(3, 0);
                int rowAffected = ps.executeUpdate();

                if (rowAffected == 1) {
                    ps = con.prepareStatement(SqlQueries.GET_SEM_REGISTRATION_ID);
                    ps.setInt(1, studentId);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        semRegId = rs.getInt("id");
                    }
                }


            }


            if (semRegId != -1) {
                ps = con.prepareStatement(SqlQueries.ADD_COURSE_STUDENT);
                ps.setInt(1, courseId);
                ps.setInt(2, semRegId);
                ps.setInt(3, isPrimary);
                ps.setInt(4, 1);
                ps.setInt(5,studentId);

                int rowAffected = ps.executeUpdate();
                if (rowAffected == 1)
                    return true;
            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean dropCourse(int courseId, int studentId) {


        try {

            PreparedStatement ps = con.prepareStatement(SqlQueries.DROP_COURSE);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);


            int rowAffected = ps.executeUpdate();

            return rowAffected == 1;


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Course> viewCourses() {
        List<Course> availableCourseList = new ArrayList<>();
        try {

            PreparedStatement ps = con.prepareStatement(SqlQueries.VIEW_COURSES_QUERY);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                availableCourseList.add(new Course(rs.getString("id"), rs.getString("professorId"), rs.getString("courseName")
                        , rs.getDouble("courseFee"), rs.getInt("studentCount")));

            }
            return availableCourseList;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return availableCourseList;
    }

    @Override
    public List<Course> viewRegisteredCourses(int studentId) {
        List<Course> registeredCourseList = new ArrayList<>();

        try {

            PreparedStatement ps = con.prepareStatement(SqlQueries.VIEW_REGISTERED_COURSES_QUERY);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                registeredCourseList.add(new Course(rs.getString("id"), rs.getString("professorId"), rs.getString("courseName")
                        , rs.getDouble("courseFee"), rs.getInt("studentCount")));

            }
            return registeredCourseList;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return registeredCourseList;
    }

    @Override
    public boolean submitChoices(int studentId) {
        try{
            PreparedStatement ps = con.prepareStatement(SqlQueries.VIEW_OPTED_COURSE_QUERY);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            ResultSet testRs= rs;
            int primaryCount=0,secondaryCount=0;
            while(testRs.next())
            {
                if(testRs.getInt("isPrimary")==0)
                    primaryCount++;
                else
                    secondaryCount++;
            }
            System.out.println("primaryCount"+primaryCount);
            System.out.println("secondaryCount"+secondaryCount);

            if(primaryCount!=4 ) {
                System.out.println("Primary Course count is"+primaryCount+"but it should be 4");
                return false;
            }
            if(secondaryCount!=2 )
            {
                System.out.println("Primary Course count is"+secondaryCount+"but it should be 2");
                return false;
            }

            while(rs.next())
            {
                int courseId= rs.getInt("courseId");
                ps=con.prepareStatement(SqlQueries.GET_STUDENT_COUNT);
                ps.setInt(1,courseId);

                ResultSet rs2= ps.executeQuery();
                System.out.println("count baby:"+ rs2.getInt(1));
                if(rs2.getInt(1)>10)
                    return false;
            }

            double fees = calculateFee(studentId);
            System.out.println("fees:"+ fees);
               if(fees==0)
               {
                   System.out.println("Error while calculating!!");
                   return false;
               }

            Notification notification= new Notification(true,"You are registered for courses and fees is"+fees,studentId);
               if(notificationDaoInterface.sendNotification(notification))
                   System.out.println("Notification Sent");

               return true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double calculateFee(int studentId) throws SQLException {
        try{
            PreparedStatement ps = con.prepareStatement(SqlQueries.CALCULATE_FEES);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getDouble(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isRegistered(String courseCode, int studentId) throws SQLException {
        return false;
    }

    @Override
    public boolean getRegistrationStatus(int studentId) throws SQLException {
        return false;
    }

    @Override
    public void setRegistrationStatus(int studentId) throws SQLException {

    }
}