package com.flipkart.dao;

import com.flipkart.bean.Grades;
import com.flipkart.constants.SqlQueries;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GradeDaoOperation implements GradeDaoInterface{
    static Connection conn = DBUtil.getConnection();
    @Override
    public boolean createGrade(int courseId, int studentId, int gpa) throws GradeNotAddedException {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.ADD_GRADE_QUERY);
            ps.setInt(1, courseId);
            ps.setInt(2, studentId);
            ps.setInt(3, gpa);

            int rowAffected = ps.executeUpdate();
            if(rowAffected==0){
                throw new GradeNotAddedException(studentId);
            }
            return rowAffected == 1;
        }
        catch(Exception e){
            System.out.println(new GradeNotAddedException(studentId));
        }
    return false;
    }
}
