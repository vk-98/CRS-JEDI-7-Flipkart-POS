package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.Roles;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementations for Admin Dao Operations
 */
public class AdminDaoOperation implements AdminDaoInterface {

    Connection conn = DBUtil.getConnection();
    UserDaoInterface userDaoInterface = new UserDaoOperation();

    /**
     * Add Course using SQL commands
     * @param course
     */
    @Override
    public void addCourse(Course course) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_COURSE);
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseDescription());
            ps.setDouble(3, course.getCourseFee());

            int rowAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Delete Course using SQL commands
     * @param courseId
     */
    @Override
    public int removeCourse(int courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.REMOVE_COURSE);
            ps.setInt(1, courseId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Fetch Students yet to approved using SQL commands
     * @return List of Students yet to approved
     */
    @Override
    public List<Student> viewPendingAdmissions() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.LIST_APPROVAL_REQUESTS);
            ResultSet rs = ps.executeQuery();
            List<Student> admissions = new ArrayList<Student>();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setUserName(rs.getString("name"));
                s.setUserEmailId(rs.getString("email"));
                admissions.add(s);
            }
            return admissions;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Approve Student using SQL commands
     * @param studentId
     */
    @Override
    public boolean approveStudent(int studentId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.APPROVE_ADDMISSION_REQUEST);
            ps.setInt(1, studentId);
            int rowAffected = ps.executeUpdate();
            return rowAffected == 1;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Add professor using SQL commands
     * @param name
     * @param emailId
     * @param password
     * @param phoneNo
     * @param department
     * @param designation
     */
    @Override
    public boolean addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation) {
        // TODO: Handle cases when professor wont be created
        boolean isSuccess = userDaoInterface.createUser(name, emailId, password, Roles.Professor, phoneNo);
        if (isSuccess) {
            int id = userDaoInterface.getUserIdByEmail(emailId);
            try {
                PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_PROFESSOR);
                ps.setInt(1, id);
                ps.setString(2, department);
                ps.setString(3, designation);
                return ps.executeUpdate() == 1;
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * View courses in the catalog
     * @return List of courses in the catalog
     */
    @Override
    public List<Course> viewCourses() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.LIST_COURSES);
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
     * View professors who enrolled in the Semester
     * @return List of the professors in the institute
     */
    @Override
    public List<Professor> viewProfessors() {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.LIST_PROFESSORS);
            ResultSet rs = ps.executeQuery();
            List<Professor> professors = new ArrayList<Professor>();
            while(rs.next()) {
                Professor p = new Professor();
                p.setProfessorId(rs.getInt("id"));
                p.setUserName(rs.getString("name"));
                p.setUserEmailId(rs.getString("email"));
                p.setDepartment(rs.getString("department"));
                p.setDesignation(rs.getString("designation"));
                professors.add(p);
            }
            return professors;
        }catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
