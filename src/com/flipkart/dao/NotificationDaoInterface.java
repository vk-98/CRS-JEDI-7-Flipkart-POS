package com.flipkart.dao;

import com.flipkart.bean.Notification;

import java.util.List;

/**
 * Interface for Notification Dao Operations
 */
public interface NotificationDaoInterface {
    /**
     * Send Notification using SQL Commands
     * @param notification: An Object of Notification which contains notification message and ID
     */
    public boolean sendNotification(Notification notification);

    /**
     * Shows all the Notifications of a Student using SQL Commands
     * @return List of Notification messages
     */
    public List<String> showNotifications(int studentId);

}
