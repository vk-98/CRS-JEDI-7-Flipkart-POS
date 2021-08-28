package com.flipkart.dao;

import com.flipkart.bean.Notification;
import com.flipkart.business.StudentOperation;
import com.flipkart.constants.SqlQueries;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JEDI-07
 * Admin Client
 */
public class NotificationDaoOperation implements NotificationDaoInterface {

    static Connection conn = DBUtil.getConnection();
    private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);


    /**
     * Method for sending the notification to student.
     *
     * @param studentId           unique Id for a student
     * @param notificationContent Content of the Notification
     * @return notification sent status
     */
    @Override
    public boolean sendNotification(int studentId, String notificationContent) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SEND_NOTIFICATION);
            ps.setInt(1, studentId);
            ps.setString(2, notificationContent);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Method to show notifications for specific studentID
     *
     * @param studentId Unique Id for a student
     * @return List of notifications
     */
    @Override
    public List<Notification> getNotifications(int studentId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueries.SHOW_NOTIFICATIONS);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            List<Notification> notifications = new ArrayList<Notification>();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("id"));
                notification.setContent(rs.getString("notificationContent"));
                notifications.add(notification);
            }
            return notifications;
        } catch (SQLException e) {
            logger.info("Error: " + e.getMessage());
        }
        return null;
    }
}
