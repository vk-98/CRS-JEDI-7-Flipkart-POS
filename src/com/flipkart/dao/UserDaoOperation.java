package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            PreparedStatement ps = con.prepareStatement(SqlQueries.ADD_USER_QUERY);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,password);
            ps.setString(4,role);
            ps.setString(5,phoneNo);

            int rowAffected= ps.executeUpdate();

            System.out.println("rowa:"+rowAffected);
            return rowAffected==1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public int getUserIdByEmail(String email) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_USER_ID);
            ps.setString(1, email);

            ResultSet result = ps.executeQuery();

            if( result.next() ) {
                return result.getInt("id");
            }

            return -1;

        }catch (SQLException e){

        }
        return -1;
    }
}