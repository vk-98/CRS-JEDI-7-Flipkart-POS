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
     * @param notificationContent
     * @return isNotificationSent
     */
    public boolean sendNotification(String notificationContent);

    /**
     * method for retreiving all the notifications
     * @return list of all the notifications
     */
    public List<Notification> getNotifications();
}
