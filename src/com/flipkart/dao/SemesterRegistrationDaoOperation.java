package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.OptedCourse;
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
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();


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
                ps.setInt(5, studentId);

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
    public List<OptedCourse> viewRegisteredCourses(int studentId) {
        List<OptedCourse> registeredCourseList = new ArrayList<OptedCourse>();

        try {

            PreparedStatement ps = con.prepareStatement(SqlQueries.VIEW_REGISTERED_COURSES_QUERY);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                registeredCourseList.add(new OptedCourse(rs.getInt("courseId"), rs.getInt("isPrimary") == 0));
            }
            return registeredCourseList;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return registeredCourseList;
    }

    @Override
    public boolean submitChoices(int studentId) {
        try {
            double fees = calculateFee(studentId);
            System.out.println("fees:" + fees);
            if (fees == 0) {
                System.out.println("Error while calculating!!");
                return false;
            }

            Notification notification = new Notification(true, "You are registered for courses and fees is" + fees, studentId);
            if (notificationDaoInterface.sendNotification(notification))
                System.out.println("Notification Sent");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Integer> getPrimarySecondaryCoursesCount(int studentId) {
        List<Integer> list = new ArrayList<Integer>();
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.VIEW_OPTED_COURSE_QUERY);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            int primaryCount = 0, secondaryCount = 0;
            while (rs.next()) {
                if (rs.getInt("isPrimary") == 0)
                    primaryCount++;
                else
                    secondaryCount++;
            }
            list.add(primaryCount);
            list.add(secondaryCount);

        } catch (SQLException e) {
            System.out.println("The error here is " + e.getMessage());
        }

        return list;
    }

    @Override
    public int getCourseIdIfSeatNotAvailable(int studentId) {

        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.VIEW_OPTED_COURSE_QUERY);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("courseId");
                ps = con.prepareStatement(SqlQueries.GET_STUDENT_COUNT);
                ps.setInt(1, courseId);

                ResultSet rs2 = ps.executeQuery();

                if (rs2.next()) {
                    if (rs2.getInt(1) > 10)
                        return courseId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public double calculateFee(int studentId) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.CALCULATE_FEES);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
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
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_REGISTRATION_STATUS);


            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean getPaymentStatus(int studentId) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_PAYMENT_STATUS);


            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1) == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setRegistrationStatus(int studentId, int status) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.SET_REGISTRATION_STATUS);

            ps.setInt(1, status);
            ps.setInt(2, studentId);

            int rowa = ps.executeUpdate();

            return rowa == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setPaymentStatus(int studentId, int status) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.SET_PAYMENT_STATUS);

            ps.setInt(1, status);
            ps.setInt(2, studentId);

            int rowa = ps.executeUpdate();

            return rowa == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
