package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoOperation implements UserDaoInterface {
    static Connection con= DBUtil.getConnection();

    @Override
    public boolean verifyCredentials(String emailId, String password) {
        return false;
    }

    @Override
    public boolean updatePassword(int userId, String newPassword) {
        return false;
    }

    @Override
    public String getRole(int userId) {
        return null;
    }

    @Override
    public boolean createUser(String name, String email, String password, String role, String phoneNo) {
        try{
            PreparedStatement ps= con.prepareStatement(SqlQueries.ADD_USER_QUERY);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,password);
            ps.setString(4,role);
            ps.setString(5,phoneNo);

            int rowAffected= ps.executeUpdate();
            return rowAffected==1;
        }catch (SQLException e){

        }
        return false;
    }
}
