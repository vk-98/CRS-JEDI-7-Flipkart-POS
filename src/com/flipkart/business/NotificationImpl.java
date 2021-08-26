package com.flipkart.business;

import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.exceptions.StudentNotRegisteredException;

import java.util.List;

public class NotificationImpl implements NotificationInterface{
    NotificationDaoInterface notificationDaoInterface= new NotificationDaoOperation();


    /**
     * Method to show notification to student
     * @param studentId
     * @return
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

    @Override
    public boolean notificationStatus(String notificationId) {
        return false;
    }
}
