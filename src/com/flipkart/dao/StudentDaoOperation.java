package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.constants.Roles;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

/**
 * @author JEDI-07
 * Implementation of Student Dao Interface
 */
public class StudentDaoOperation implements StudentDaoInterface {

    static Connection conn = DBUtil.getConnection();
    private static Logger logger = Logger.getLogger(StudentDaoOperation.class);

    /**
     * Method to add Student to Database
     *
     * @param studentName     Name of the Student
     * @param studentEmailId  EmailId of the Student
     * @param studentPassword Password for the Student
     * @param studentPhoneNo  Phone Number of the Student
     * @return returns true if student is added successfully to the database
     */
    @Override
    public boolean addStudent(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) {
        UserDaoInterface userDaoInterface = new UserDaoOperation();

        boolean isSuccess = userDaoInterface.createUser(studentName, studentEmailId, studentPassword, Roles.Student, studentPhoneNo);

        if (isSuccess) {
            int id = userDaoInterface.getUserIdByEmail(studentEmailId);
            try {
                PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_STUDENT);
                ps.setInt(1, id);
                ps.setInt(2, 0);

                int rowAffected = ps.executeUpdate();

                return (rowAffected == 1);

            } catch (SQLException e) {
                logger.info("Error: " + e.getMessage());
            }
        }
        return false;
    }


    /**
     * Method for getting student by Email Id
     *
     * @param emailId Email Id of the Student
     * @return Returns Student Object
     */
    @Override
    public Student getStudentByEmailId(String emailId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_STUDENT_BY_EMAIL_ID);
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student st = new Student();
                st.setStudentId(rs.getInt("id"));
                st.setUserEmailId(rs.getString("email"));
                st.setUserName(rs.getString("name"));
                st.setPhoneNo(rs.getString("phone"));
                st.setApproved(rs.getInt("isApproved") == 1);
                return st;
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    /**
     * Method for getting student by student Id
     *
     * @param studentId Unique Id of the Student
     * @return Returns Student Object
     */
    @Override
    public Student getStudentByStudentId(int studentId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_STUDENT_BY_STUDENT_ID);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student st = new Student();
                st.setId(studentId);
                st.setApproved(rs.getInt("isApproved") == 1);
                return st;
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    /**
     * Method to get Grades of a Student
     *
     * @param studentId Unique Id of the Student
     * @return List of Grades
     */
    @Override
    public List<Grade> getGrades(int studentId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.GET_GRADES);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            List<Grade> grades = new ArrayList<Grade>();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setCourseId(rs.getInt("courseId"));
                grade.setCourseName(rs.getString("courseName"));
                grade.setGpa(rs.getDouble("gpa"));
                grades.add(grade);
            }
            return grades;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return null;
    }
}