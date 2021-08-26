package com.flipkart.dao;

import com.flipkart.bean.Notification;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementations for Notification Dao Operations
 */
public class NotificationDaoOperation implements NotificationDaoInterface {
    static Connection con = DBUtil.getConnection();

    /**
     * Send Notification using SQL Commands
     * @param notification: An Object of Notification which contains notification message and ID
     */
    @Override
    public boolean sendNotification(Notification notification) {
      try{
          PreparedStatement ps = con.prepareStatement(SqlQueries.SEND_NOTIFICATION);

          ps.setString(1, notification.getContent());
          ps.setInt(2,notification.getStudentId());

          int rowAffected = ps.executeUpdate();

          return rowAffected==1;
      }
      catch(SQLException e){
          e.printStackTrace();
      }
        return false;
    }

    /**
     * Shows all the Notifications of a Student using SQL Commands
     * @return List of Notification messages
     */
    @Override
    public List<String> showNotifications(int studentId) {
        List<String > notifications= new ArrayList<String>();
        try{
            PreparedStatement ps = con.prepareStatement(SqlQueries.SHOW_NOTIFICATIONS);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                notifications.add(rs.getString("notificationName"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return notifications;
    }
}
