package com.flipkart.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Interface for Closing Connection
 */
public interface CloseConnection {
    //default function to close connections
    default public void closeConnection(Connection conn, PreparedStatement stmt) {

        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException se2) {
        }// nothing we can do
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }


    }

}
