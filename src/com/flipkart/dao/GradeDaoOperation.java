package com.flipkart.dao;

import com.flipkart.bean.Grades;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GradeDaoOperation implements GradeDaoInterface{
    static Connection conn = DBUtil.getConnection();
    @Override
    public boolean createGrade(int courseId, int studentId, int gpa) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_GRADE_QUERY);
            ps.setInt(1, courseId);
            ps.setInt(2, studentId);
            ps.setInt(3, gpa);

            int rowAffected = ps.executeUpdate();

            //System.out.println("rowa:" + rowAffected);
            return rowAffected == 1;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
