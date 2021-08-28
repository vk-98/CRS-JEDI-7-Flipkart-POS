package com.flipkart.business;

import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

import java.util.List;

public class NotificationImpl implements NotificationOperation {
    NotificationDaoInterface notificationDaoInterface= new NotificationDaoOperation();

    @Override
    public boolean sendNotification(String notificationContent) {
        return notificationDaoInterface.sendNotification(notificationContent);
    }

    @Override
    public List<Notification> getNotifications() {
        return notificationDaoInterface.getNotifications(StudentOperation.student.getStudentId());
    }


}
