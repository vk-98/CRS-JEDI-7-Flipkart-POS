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
 * SmesterRegistration for DAO Operation
 */
public class SemesterRegistrationDaoOperation implements SemesterRegistrationDaoInterface {

    static Connection conn = DBUtil.getConnection();
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    private static Logger logger = Logger.getLogger(SemesterRegistrationDaoOperation.class);

    /**
     * method for Student registration for Semester
     *
     * @return returns true if student successfully registers
     */
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

    /**
     * method to get Semester Id
     *
     * @return returns the semesterId
     */
    @Override
    public int getSemesterId() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_SEMESTER_ID);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * method to get number of courses selected by student
     *
     * @param isPrimary Indicates if the course is primary or not
     * @return returns the number of courses selected by student
     */
    @Override
    public int getCourseCount(int isPrimary) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_COURSE_COUNT);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ps.setInt(2, isPrimary);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * method to check if a course is available for the semster
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is available for semester
     */
    @Override
    public boolean checkAvailability(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.CHECK_COURSE_AVAILABILITY);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("studentCount") < 10;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * method to get the Registration status of Semester by student
     *
     * @return returns true if he has successfully registerd for the semester
     */
    @Override
    public boolean getRegistrationStatus() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_REGISTRATION_STATUS);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("registrationStatus") == 1;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to add course
     *
     * @param courseId   unique Id to represent a course
     * @param semesterId Id of the Current Semester
     * @param isPrimary  Indicates if the course is primary or not
     * @return returns true if the course is added successfully
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
     * Method to Drop Course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is dropped successfully
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
     * Method to View all the courses
     *
     * @return List of courses
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
     * Method to Get List of Registered  courses of the Student for a semester
     *
     * @return List of Registered Courses
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
                oc.setPrimary(rs.getInt("isPrimary") == 1);

                registeredCourseList.add(oc);
            }
            return registeredCourseList;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }

        return null;
    }

    /**
     * Method to Get List of Selected  courses of the Student for a semester
     *
     * @return List of Selected Courses
     */
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
                oc.setPrimary(rs.getInt("isPrimary") == 1);

                registeredCourseList.add(oc);
            }
            return registeredCourseList;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }

        return null;
    }


    /**
     * Method to get count of Primary and Secondary courses of a student for a Semester
     *
     * @param studentId unique Id for a student
     * @return List of count of Primary and Secondary courses
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
     * Method to get courseId if seat is not available
     *
     * @param studentId unique Id for a student
     * @return Returns CourseId if seat is not available
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
     * Method to calculate fee for the Semester to a student
     *
     * @param studentId unique Id for a student
     * @return returns Calculated Semester fee
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
     * Method to find if course is already registered
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is already registered or not
     */
    @Override
    public boolean isCourseAlreadyRegistered(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.CHECK_COURSE_STUDENT);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ps.setInt(2, courseId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) >= 1;
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return false;
    }


    /**
     * Method to get registration status of a student for a semester
     *
     * @param studentId unique Id for a student
     * @return returns true if the student is registered for the semester
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
     * Method to Get Payment Status of student
     *
     * @return returns true if Payment is done successfully by the student
     */
    @Override
    public boolean getPaymentStatus() {
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
     * Method to Set Registration status of the student
     *
     * @param studentId unique Id for a student
     * @param status    the status to be set
     * @return returns true if registration status is set successfully
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
     * Method to Set Payment status of the student
     *
     * @param studentId unique Id for a student
     * @param status    the status to be set
     * @return returns true if payment status is set successfully
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
     * Method to allot course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is allotted successfully
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
     * Method to Submit Registration
     *
     * @param courseFee Fee assigned to a course
     * @return returns true if registration submitted successfully
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
     * Method to Update Student count for a course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if Student count is updated
     */
    @Override
    public boolean updateStudentCount(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.UPDATE_STUDENT_COUNT);
            ps.setInt(1, courseId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to get Pending fee
     *
     * @return returns the pending fee
     */
    @Override
    public double getPendingFee() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_PENDING_FEE);
            ps.setInt(1, StudentOperation.student.getStudentId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("feeStatus") == 0 && rs.getInt("registrationStatus") == 1) {
                    return rs.getDouble("totalFees");
                }
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Method to Pay fee
     *
     * @param amount Amount to be paid
     * @return returns true if payment is successful
     */

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
