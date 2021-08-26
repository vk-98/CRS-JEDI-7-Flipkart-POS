package com.flipkart.dao;

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
    static Connection con = DBUtil.getConnection();
    private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);


    /**
     * Method to send notification
     * @param notification object
     * @return boolean indicating if the notification is sent successfully
     * @throws SQLException
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
            logger.info(logger.getClass());
            logger.error(e.getMessage());
        }
        return false;
    }


    /**
     * Method to show notifications for specific studentID
     * @param studentId
     * @return list of notifications
     * @throws SQLException
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
            logger.info(logger.getClass());
            logger.error(e.getMessage());
        }
        return notifications;
    }
}
