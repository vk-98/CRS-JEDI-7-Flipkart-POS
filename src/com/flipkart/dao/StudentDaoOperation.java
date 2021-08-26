package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Student;
import com.flipkart.constants.SqlQueries;
import com.flipkart.exceptions.StudentNotFoundException;
import com.flipkart.utils.DBUtil;

/**
 * Implementations for Student Dao Operations
 */
public class StudentDaoOperation implements StudentDaoInterface {

    static Connection con = DBUtil.getConnection();

    /**
     * Method to add student to database
     * @param student: student object containing all the fields
     * @return true if student is added, else false
     */
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


    /**
     * Method to retrieve Student Id from User Id
     * @param userId
     * @return Student Id
     */
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
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Method to check if Student is approved
     * @param studentId
     * @return boolean indicating if student is approved
     */
    @Override
    public boolean isApproved(int studentId) {
        return false;
    }

    /**
     * Method to retrieve Student from Student Id
     * @param studentId
     * @return Student : Object of Student
     */
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
            System.out.println(e.getMessage());
        }
        return null;
    }

}