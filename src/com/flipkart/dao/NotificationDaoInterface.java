package com.flipkart.dao;

import com.flipkart.bean.Notification;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JEDI-07
 * Admin Client
 */
public interface NotificationDaoInterface {
    public boolean sendNotification(String notificationContent);

    public List<Notification> getNotifications(int studentId);

}
