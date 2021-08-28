package com.flipkart.business;

import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.exceptions.StudentNotRegisteredException;

import java.sql.SQLException;
import java.util.List;

public class NotificationImpl implements NotificationInterface{
    NotificationDaoInterface notificationDaoInterface= new NotificationDaoOperation();

    @Override
    public boolean sendNotification(String notificationContent) {
        return notificationDaoInterface.sendNotification(notificationContent);
    }

    @Override
    public List<Notification> getNotifications() {
        return notificationDaoInterface.getNotifications(StudentInterfaceImpl.student.getStudentId());
    }


}
