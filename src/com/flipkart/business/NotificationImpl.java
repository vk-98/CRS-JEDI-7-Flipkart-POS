package com.flipkart.business;

import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

import java.util.List;

/**
 * Implementations for Notification Handling
 */
public class NotificationImpl implements NotificationInterface{
    NotificationDaoInterface notificationDaoInterface= new NotificationDaoOperation();

    /**
     * Method to display all the Notifications for a corresponding student
     * @param studentId
     */
    @Override
    public void showNotifications(int studentId) {
        List<String > notifications= notificationDaoInterface.showNotifications(studentId);

        if(notifications.size()==0)
            System.out.println("#### No notifications to show");
        else
        {
            int i=1;
            System.out.println("### Notifications-");
            for(String notification :notifications)
            {
                System.out.println(i++ +": "+notification);
            }
        }
    }

    /**
     * Method to check the status of the Notification
     * @param notificationId
     */
    @Override
    public boolean notificationStatus(String notificationId) {
        return false;
    }
}
