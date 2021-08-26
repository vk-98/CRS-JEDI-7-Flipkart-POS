package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.OptedCourse;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SemesterRegistrationDaoOperation implements SemesterRegistrationDaoInterface {
    static Connection con = DBUtil.getConnection();
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    private static Logger logger = Logger.getLogger(SemesterRegistrationDaoOperation.class);


    /**
     * Method to add a course for a given student
     * @param courseId
     * @param studentId
     * @param isPrimary
     * @return boolean indicating if the course is added successfully
     * @throws SQLException
     */
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
            logger.info(logger.getClass());
            logger.error(e.getMessage());
        }
        return false;
    }



    /**
     * Method to drop a course chosen by a student
     * @param courseId
     * @param studentId
     * @return boolean indicating if the course is dropped successfully
     * @throws SQLException
     */
    @Override
    public boolean dropCourse(int courseId, int studentId) {


        try {

            PreparedStatement ps = con.prepareStatement(SqlQueries.DROP_COURSE);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);


            int rowAffected = ps.executeUpdate();

            return rowAffected == 1;


        } catch (SQLException e) {
            logger.info(logger.getClass());
            logger.error(e.getMessage());
        }
        return false;
    }


    /**
     * Method to view all available courses
     * @return list of courses available to register
     * @throws SQLException
     */
    @Override
    public List<Course> viewCourses() {
        List<Course> availableCourseList = new ArrayList<>();
        try {

            PreparedStatement ps = con.prepareStatement(SqlQueries.VIEW_COURSES_QUERY);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt("id"));
                c.setCourseName(rs.getString("courseName"));
                c.setCourseDescription(rs.getString("courseDescription"));
                c.setCourseFee(rs.getDouble("courseFee"));
                c.setStudentCount(rs.getInt("studentCount"));
                c.setProfessorId(rs.getInt("professorId"));
                availableCourseList.add(c);
            }
            return availableCourseList;
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return availableCourseList;
    }


    /**
     * Method to view all registered courses of the given student
     * @param studentId
     * @return list of all courses registered by the student
     * @throws SQLException
     */
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
            logger.info(e.getMessage());
        }

        return registeredCourseList;
    }


    /**
     * Method to submit course choices
     * @param studentId
     * @return boolean indicating if all the choices are approved and the registration is done successfully
     * @throws SQLException
     */
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
            logger.info(logger.getClass());
            logger.error(e.getMessage());
        }
        return false;
    }


    /**
     * Method to get count of primary and secondary courses opted by a student
     * @param studentId
     * @return primary and secondary course counts
     * @throws SQLException
     */
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
            logger.info(e.getMessage());
        }

        return list;
    }


    /**
     * Method to get the first opted courseID among all the courses chosen by the student whose seat is not available
     * @param studentId
     * @return the first courseID or -1 if all courses can accommodate the  student
     * @throws SQLException
     */
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
            logger.info(e.getMessage());
        }

        return -1;
    }


    /**
     * Method to calculate total fee
     * @param studentId
     * @return total semester fee
     * @throws SQLException
     */
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
            logger.info(e.getMessage());
        }
        return 0;
    }


    /**
     * Method to check if the student is already registered for the specified course
     * @param studentId
     * @param courseId
     * @return boolean indicating if the student is already registered for the course
     * @throws SQLException
     */
    @Override
    public boolean isRegistered(int courseId, int studentId) throws SQLException {
        try{
            PreparedStatement ps= con.prepareStatement(SqlQueries.CHECK_COURSE_STUDENT);
            ps.setInt(1,studentId);
            ps.setInt(2,courseId);

            ResultSet rs= ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt(1)>=1;
            }
        }catch(SQLException e){
            logger.info(e.getMessage());
        }
        return false;
    }


    /**
     * Method to get registration status of the student
     * @param studentId
     * @return boolean indicating the registration status of the student
     * @throws SQLException
     */
    @Override
    public boolean getRegistrationStatus(int studentId) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_REGISTRATION_STATUS);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return false;

    }

    /**
     * Method to get payment status of the student
     * @param studentId
     * @return boolean indicating the payment status
     * @throws SQLException
     */
    @Override
    public boolean getPaymentStatus(int studentId) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_PAYMENT_STATUS);


            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt(1) == 1;
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return false;
    }


    /**
     * Method to set registration status of the student
     * @param studentId
     * @param status
     * @return boolean indicating if registration status of the student is updated as requested
     * @throws SQLException
     */
    @Override
    public boolean setRegistrationStatus(int studentId, int status) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.SET_REGISTRATION_STATUS);

            ps.setInt(1, status);
            ps.setInt(2, studentId);

            int rowa = ps.executeUpdate();

            return rowa == 1;
        } catch (SQLException e) {
            logger.info(logger.getClass());
            logger.error(e.getMessage());
        }
        return false;
    }


    /**
     * Method to set status of payment for a given student
     * @param studentId
     * @param status
     * @return boolean indicating if payment status of the student is updated as requested
     * @throws SQLException
     */
    @Override
    public boolean setPaymentStatus(int studentId, int status) throws SQLException {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.SET_PAYMENT_STATUS);

            ps.setInt(1, status);
            ps.setInt(2, studentId);

            int rowa = ps.executeUpdate();

            return rowa == 1;
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return false;
    }
}
