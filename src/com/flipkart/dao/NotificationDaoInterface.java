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
     * @param studentId
     * @param notificationContent
     * @return notification sent status
     */
    public boolean sendNotification(int studentId, String notificationContent);

    public List<Notification> getNotifications(int studentId);

}
