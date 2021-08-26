package com.flipkart.business;

import com.flipkart.bean.Notification;

/**
 * Interface for Notification Handling
 */
public interface NotificationInterface {

    /**
     * Method to display all the Notifications for a corresponding student
     * @param studentId
     */
    void showNotifications(int studentId);

    /**
     * Method to check the status of the Notification
     * @param notificationId
     */
    boolean notificationStatus(String notificationId);
}
