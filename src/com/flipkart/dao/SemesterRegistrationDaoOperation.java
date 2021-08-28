package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.OptedCourse;
import com.flipkart.business.StudentOperation;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JEDI-07
 * Admin Client
 */
public class SemesterRegistrationDaoOperation implements SemesterRegistrationDaoInterface {

    static Connection conn = DBUtil.getConnection();
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    private static Logger logger = Logger.getLogger(SemesterRegistrationDaoOperation.class);


    @Override
    public boolean registerForSemester() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_SEMESTER_REGISTRATION);
            ps.setInt(1, StudentOperation.student.getStudentId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public int getSemesterId() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_SEMESTER_ID);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int getCourseCount(int isPrimary) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_COURSE_COUNT);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ps.setInt(2, isPrimary);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean checkAvailability(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.CHECK_COURSE_AVAILABILITY);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("studentCount") < 10;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean getRegistrationStatus() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_REGISTRATION_STATUS);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("registrationStatus")==1;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to add a course for a given student
     * @param courseId
     * @param isPrimary
     * @return boolean indicating if the course is added successfully
     * @throws SQLException
     */
    @Override
    public boolean addCourse(int courseId, int semesterId, int isPrimary) {

        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_COURSE_STUDENT);
            ps.setInt(1, courseId);
            ps.setInt(2, semesterId);
            ps.setInt(3, isPrimary);
            ps.setInt(4, StudentOperation.student.getStudentId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Erros: " + e.getMessage());
        }
        return false;
    }



    /**
     * Method to drop a course chosen by a student
     * @param courseId
     * @return boolean indicating if the course is dropped successfully
     * @throws SQLException
     */
    @Override
    public boolean dropCourse(int courseId) {
        try {

            PreparedStatement ps = conn.prepareStatement(SqlQueries.DROP_COURSE);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ps.setInt(2, courseId);

            int rowAffected = ps.executeUpdate();
            return rowAffected == 1;
        } catch (SQLException e) {
           logger.info("Error: " + e.getMessage());
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

            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_COURSES_QUERY);


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
            logger.info("Error: " + e.getMessage());
        }
        return availableCourseList;
    }


    /**
     * Method to view all registered courses of the given student
     * @return list of all courses registered by the student
     * @throws SQLException
     */
    @Override
    public List<OptedCourse> getRegisteredCourses() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_REGISTERED_STUDENT_COURSES);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            List<OptedCourse> registeredCourseList = new ArrayList<OptedCourse>();

            while (rs.next()) {
                OptedCourse oc = new OptedCourse();
                oc.setCourseId(rs.getInt("id"));
                oc.setCourseName(rs.getString("courseName"));
                oc.setStudentCount(rs.getInt("studentCount"));
                oc.setCourseFee(rs.getDouble("courseFee"));
                oc.setPrimary(rs.getInt("isPrimary")==1);

                registeredCourseList.add(oc);
            }
            return registeredCourseList;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<OptedCourse> getSelectedCourses() {
        try {

            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_SELECTED_STUDENT_COURSES);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            List<OptedCourse> registeredCourseList = new ArrayList<OptedCourse>();

            while (rs.next()) {
                OptedCourse oc = new OptedCourse();
                oc.setCourseId(rs.getInt("id"));
                oc.setCourseName(rs.getString("courseName"));
                oc.setStudentCount(rs.getInt("studentCount"));
                oc.setCourseFee(rs.getDouble("courseFee"));
                oc.setPrimary(rs.getInt("isPrimary")==1);

                registeredCourseList.add(oc);
            }
            return registeredCourseList;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }

        return null;
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
            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_OPTED_COURSE_QUERY);
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
            logger.info("Error " + e.getMessage());
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
            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_OPTED_COURSE_QUERY);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("courseId");
                ps = conn.prepareStatement(SqlQueries.GET_STUDENT_COUNT);
                ps.setInt(1, courseId);

                ResultSet rs2 = ps.executeQuery();

                if (rs2.next()) {
                    if (rs2.getInt(1) > 10)
                        return courseId;
                }
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
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
            PreparedStatement ps = conn.prepareStatement(SqlQueries.CALCULATE_FEES);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
           logger.info("Error: " + e.getMessage());
        }
        return 0;
    }


    /**
     * Method to check if the student is already registered for the specified course
     * @param courseId
     * @return boolean indicating if the student is already registered for the course
     * @throws SQLException
     */
    @Override
    public boolean isCourseAlreadyRegistered(int courseId)  {
        try{
            PreparedStatement ps= conn.prepareStatement(SqlQueries.CHECK_COURSE_STUDENT);
            ps.setInt(1, StudentOperation.student.getStudentId());
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
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_REGISTRATION_STATUS);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;

    }

    /**
     * Method to get payment status of the student
     * @return boolean indicating the payment status
     */
    @Override
    public boolean getPaymentStatus()  {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_PAYMENT_STATUS);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt(1) == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
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
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SET_REGISTRATION_STATUS);

            ps.setInt(1, status);
            ps.setInt(2, studentId);

            int rowa = ps.executeUpdate();

            return rowa == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
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
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SET_PAYMENT_STATUS);

            ps.setInt(1, status);
            ps.setInt(2, studentId);

            int rowa = ps.executeUpdate();

            return rowa == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method for allocating a course to student.
     * @param courseId
     * @return
     */
    @Override
    public boolean allotCourse(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ALLOT_COURSE);
            ps.setInt(1, courseId);
            ps.setInt(2, StudentOperation.student.getStudentId());
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method for registering for the semester, marking registered=true and updating fee.
     * @param courseFee
     * @return
     */
    @Override
    public boolean submitRegistration(double courseFee) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SUBMIT_REGISTRATION);
            ps.setDouble(1, courseFee);
            ps.setInt(2, StudentOperation.student.getStudentId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method for updating the student count in courses table.
     * @param courseId
     * @return
     */
    @Override
    public boolean updateStudentCount(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.UPDATE_STUDENT_COUNT);
            ps.setInt(1,courseId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public double getPendingFee() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_PENDING_FEE);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                if(rs.getInt("feeStatus") == 0 && rs.getInt("registrationStatus")==1) {
                    return rs.getDouble("totalFees");
                }
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean payFee(double amount) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.PAY_FEE);
            ps.setInt(1, StudentOperation.student.getStudentId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }




}
