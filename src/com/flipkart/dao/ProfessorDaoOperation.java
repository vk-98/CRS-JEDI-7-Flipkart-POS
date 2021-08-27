package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.ProfessorInterfaceImpl;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
    Implementation for Professor Dao Operation
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {
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
            System.out.println("Error: " + e.getMessage());

        }
        return null;
    }

    /**
     * Method to retrieve all the courses according to ProfessorId
     * @return List of courses
     */
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
            System.out.println("Error: " + e.getMessage());
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
            System.out.println("Error: " + e.getMessage());
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
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    /**
     * Method to check if given student is already graded in a given course
     * @param studentId
     * @param courseId
     */
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
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    /**
     * Method to show the List of available courses
     * @return List of available courses
     */
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
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Method to check if given course is available or not
     * @param courseId
     * @return availability of the given course
     */
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
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    /**
     * Method to select a course
     * @param courseId
     */
    @Override
    public boolean selectCourse(int courseId) {
        System.out.println("inside dao");
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SELECT_COURSE_FOR_PROF);
            ps.setInt(1, ProfessorInterfaceImpl.professor.getProfessorId());
            ps.setInt(2, courseId);
            return ps.executeUpdate() == 1;
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to check if course is selected or not
     * @param courseId
     */
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
            System.out.println("Error: " + e.getMessage());
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
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    /**
     * Method to check if a given student is enrolled in a given course
     * @param studentId
     * @param courseId
     */
    @Override
    public boolean IsStudentEnrolled(int studentId, int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.IS_STUDENT_ENROLLED);
            ps.setInt(1,studentId);
            ps.setInt(2,courseId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return true;
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
