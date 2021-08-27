package com.flipkart.dao;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Notification;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDaoOperation implements NotificationDaoInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    static Connection con = DBUtil.getConnection();
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
          logger.info("Error: " + e.getMessage());
      }
        return false;
    }

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
            logger.info("Error: " + e.getMessage());
        }
        return notifications;
    }
}
