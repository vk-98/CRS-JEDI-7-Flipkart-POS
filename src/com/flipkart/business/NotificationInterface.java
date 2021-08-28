package com.flipkart.business;

import com.flipkart.bean.Notification;

import java.util.List;

/**
 * @author JEDI-07
 * Notification Interface
 */
public interface NotificationInterface {
    /**
     * method for sending notification to student
     *
     * @param notificationContent Content of the Notification
     * @return returns true if the Notification is sent successfully
     */
    public boolean sendNotification(String notificationContent);

    /**
     * method for retrieving all the notifications
     *
     * @return list of all the notifications
     */
    public List<Notification> getNotifications();
}
