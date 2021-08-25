package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.flipkart.bean.Student;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

public class StudentDaoOperation implements StudentDaoInterface {

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
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public int getStudentById(int userId) {

        return 0;
    }

    @Override
    public boolean isApproved(int studentId) {
        return false;
    }

}