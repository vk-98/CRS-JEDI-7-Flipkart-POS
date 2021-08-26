package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoOperation implements CourseDaoInterface
{
    static Connection con = DBUtil.getConnection();

    @Override
    public List<Course> getCoursesByProfessorId(int professorId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_COURSES_BY_PROFESSOR_ID);
            ps.setInt(1, professorId);
            ResultSet result = ps.executeQuery();
            List<Course> courses = new ArrayList<Course>();
            while(result.next()) {
                int id = result.getInt("id");
                String courseName = result.getString("courseName");
                String courseDescription = result.getString("courseDescription");
                double courseFee = result.getDouble("courseFee");
                int studentCount = result.getInt("studentCount");
                int studentId = result.getInt("studentId");
                Course course = new Course(String.valueOf(id), String.valueOf(professorId), courseName, courseFee, studentCount, courseDescription, studentId);
                courses.add(course);

            }
            return courses;

        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Integer> getStudentIdsByProfessorId(int professorId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_STUDENT_ID_BY_PROFESSOR_ID);
            ps.setInt(1, professorId);
            ResultSet result = ps.executeQuery();
            List<Integer> studentIds = new ArrayList<Integer>();
            while(result.next()) {
                int studentId = result.getInt("studentId");
                studentIds.add(studentId);
            }
            return studentIds;

        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int getProfessorIdByCourseId(int courseId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_PROFESSOR_ID_BY_COURSE_ID);
            ps.setInt(1, courseId);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                return result.getInt("professorId");
            }
            return -1;

        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public void setProfessorToCourse(int professorId, int courseId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.SET_PROFESSOR_TO_COURSE);
            ps.setInt(1, professorId);
            ps.setInt(2, courseId);
            ps.executeUpdate();

        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
