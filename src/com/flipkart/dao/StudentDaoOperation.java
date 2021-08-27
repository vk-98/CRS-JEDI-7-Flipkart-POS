package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Student;
import com.flipkart.constants.SqlQueries;
import com.flipkart.exceptions.StudentNotFoundException;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

public class StudentDaoOperation implements StudentDaoInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    static Connection con = DBUtil.getConnection();

    @Override
    public boolean addStudent(Student student) {
        UserDaoInterface userDaoInterface = new UserDaoOperation();

        boolean isSuccess = userDaoInterface.createUser(student.getUserName(), student.getUserEmailId(), student.getUserPassword(), student.getRole(), student.getPhoneNo());

        if (isSuccess) {
            int id = userDaoInterface.getUserIdByEmail(student.getUserEmailId());
            try {
                PreparedStatement ps = con.prepareStatement(SqlQueries.ADD_STUDENT);
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

    @Override
    public int getStudentById(int userId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_STUDENT_ID);
            ps.setInt(1, userId);


            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return rs.getInt("id");
            }



        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean isApproved(int studentId) {
        return false;
    }

    @Override
    public Student getStudentByEmailId(String emailId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_STUDENT_BY_EMAIL_ID);
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
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
    @Override
    public Student getStudentByStudentId(int studentId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_STUDENT_BY_STUDENT_ID);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
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

}