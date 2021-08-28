package com.flipkart.dao;

import com.flipkart.bean.Notification;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JEDI-07
 * Admin Client
 */
public interface NotificationDaoInterface {
    /**
     * Method for sending the notification to student.
     *
     * @param studentId           unique Id for a student
     * @param notificationContent Content of the Notification
     * @return notification sent status
     */
    public boolean sendNotification(int studentId, String notificationContent);

    /**
     * Method for retrieving Notifications
     *
     * @param studentId unique Id for a student
     * @return List of Notifications
     */
    public List<Notification> getNotifications(int studentId);

}
