package com.flipkart.business;

import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

import java.util.List;

/**
 * @author JEDI-07
 * Implementation of Notification Interface
 */
public class NotificationOperation implements NotificationInterface {
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    /**
     * method for sending notification to student
     * @param notificationContent
     * @return
     */
    @Override
    public boolean sendNotification(String notificationContent) {
        return notificationDaoInterface.sendNotification(StudentOperation.student.getStudentId(), notificationContent);
    }

    /**
     * method for retreiving all the notifications
     * @return list of all the notifications
     */
    @Override
    public List<Notification> getNotifications() {
        return notificationDaoInterface.getNotifications(StudentOperation.student.getStudentId());
    }
}
