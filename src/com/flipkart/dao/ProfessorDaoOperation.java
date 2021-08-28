package com.flipkart.dao;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.ProfessorOperation;
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
 * Implementation of Professofor doa interface
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    Connection conn = DBUtil.getConnection();

    /**
     * Method to retrieve Professor Details using userId
     * @param userId
     * @return Professor Details
     */
    @Override
    public Professor getProfessorByUserId(int userId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_PROFESSOR_BY_USER_ID);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Professor professor = new Professor();
                professor.setProfessorId(rs.getInt("id"));
                professor.setDesignation(rs.getString("designation"));
                professor.setDepartment(rs.getString("department"));
                professor.setUserName(rs.getString("name"));
                professor.setUserEmailId(rs.getString("email"));
                return professor;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());

        }
        return null;
    }

    /**
     * Method to retrieve all the courses according to ProfessorId
     * @param professorId
     * @return List of courses
     */
    @Override
    public List<Course> getCoursesByProfessorId(int professorId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_SELECTED_COURSES_FOR_PROF);
            ps.setInt(1, professorId);
            ResultSet rs = ps.executeQuery();

            List<Course> courses = new ArrayList<Course>();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt("id"));
                c.setCourseName(rs.getString("courseName"));
                c.setCourseDescription(rs.getString("courseDescription"));
                c.setCourseFee(rs.getDouble("courseFee"));
                c.setStudentCount(rs.getInt("studentCount"));
                c.setProfessorId(rs.getInt("professorId"));
                courses.add(c);
            }
            return courses;
        } catch (SQLException e) {
           logger.info("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to retrieve the list of all enrolled students in a given course
     * @param courseId
     * @return List of all enrolled students in a given course
     */
    @Override
    public List<Student> getEnrolledStudents(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_ENROLLED_STUDENTS);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            List<Student> students = new ArrayList<Student>();
            while (rs.next()) {
                Student st = new Student();
                st.setStudentId(rs.getInt("id"));
                st.setUserName(rs.getString("name"));
                st.setUserEmailId(rs.getString("email"));
                st.setPhoneNo(rs.getString("phone"));
                students.add(st);
            }
            return students;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return null;
    }
    /**
     * Method to add grade in the database for given student in given course
     * @param studentId
     * @param courseId
     * @param grade
     * @return if grade is added successfully or not
     */

    @Override
    public Boolean addGrade(int studentId, int courseId, double grade) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_GRADE);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setDouble(3, grade);
            int rowAffected = ps.executeUpdate();
            return rowAffected==1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return null;
    }
    /**
     * Method to check if given student is already graded in a given course
     * @param studentId
     * @param courseId
     */
    @Override
    public boolean isStudentAlreadyGraded(int studentId, int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.IS_STUDENT_ALREADY_GRADED);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }
    /**
     * method to show the List of available courses
     * @return List of available courses
     */
    @Override
    public List<Course> getAvailableCourses() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_AVAILABLE_COURSES_PROFESSOR);
            ResultSet rs = ps.executeQuery();
            List<Course> courses = new ArrayList<Course>();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseName(rs.getString("courseName"));
                c.setCourseDescription(rs.getString("courseDescription"));
                c.setCourseId(rs.getInt("id"));
                c.setCourseFee(rs.getDouble("courseFee"));
                c.setStudentCount(rs.getInt("studentCount"));
                courses.add(c);
            }
            return courses;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * method to check if given course is available or not
     * @param courseId
     * @return availability of the given course
     */
    @Override
    public boolean isCourseAvailable(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.COURSE_AVAILABLE_FOR_PROF);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("professorId") == 0;
            }
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * method to select a course
     * @param professorId
     * @param courseId
     * @return isCourseSelected
     */
    @Override
    public boolean selectCourse(int professorId, int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SELECT_COURSE_FOR_PROF);
            ps.setInt(1, professorId);
            ps.setInt(2, courseId);
            return ps.executeUpdate() == 1;
        }catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to check if course is selected or not
     * @param professorId
     * @param courseId
     * @return is Course already selected by professor.
     */
    @Override
    public boolean isCourseSelected(int professorId, int courseId) {
        try{
            PreparedStatement ps = conn.prepareStatement(SqlQueries.IS_COURSE_SELECTED_BY_PROF);
            ps.setInt(1, professorId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }
    /**
     * Method to deselect the course
     * @param courseId
     */
    @Override
    public boolean deselectCourse(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.DELSELECT_COURSE_FOR_PROF);
            ps.setInt(1, courseId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
           logger.info("Error: " + e.getMessage());
        }
        return false;
    }
    /**
     * Method to check if a given student is enrolled in a given course
     * @param studentId
     * @param courseId
     *
     */
    @Override
    public boolean isStudentEnrolled(int studentId, int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.IS_STUDENT_ENROLLED);
            ps.setInt(1,studentId);
            ps.setInt(2,courseId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return true;
        }catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }
}
