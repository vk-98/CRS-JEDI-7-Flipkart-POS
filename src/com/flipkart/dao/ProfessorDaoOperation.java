package com.flipkart.dao;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.ProfessorInterfaceImpl;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDaoOperation implements ProfessorDaoInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    Connection conn = DBUtil.getConnection();

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

    @Override
    public List<Course> getCoursesByProfessorId() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.VIEW_SELECTED_COURSES_FOR_PROF);
            ps.setInt(1, ProfessorInterfaceImpl.professor.getProfessorId());
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

    @Override
    public boolean IsStudentAlreadyGraded(int studentId, int courseId) {
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

    @Override
    public List<Course> viewAvailableCourses() {
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

    @Override
    public boolean IsCourseAvailable(int courseId) {
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

    @Override
    public boolean selectCourse(int courseId) {
        System.out.println("inside dao");
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SELECT_COURSE_FOR_PROF);
            ps.setInt(1, ProfessorInterfaceImpl.professor.getProfessorId());
            ps.setInt(2, courseId);
            return ps.executeUpdate() == 1;
        }catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean IsCourseSelected(int courseId) {
        try{
            PreparedStatement ps = conn.prepareStatement(SqlQueries.IS_COURSE_AVAILABLE_FOR_PROF);
            ps.setInt(1, courseId);
            ps.setInt(2, ProfessorInterfaceImpl.professor.getProfessorId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

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

    @Override
    public boolean IsStudentEnrolled(int studentId, int courseId) {
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
