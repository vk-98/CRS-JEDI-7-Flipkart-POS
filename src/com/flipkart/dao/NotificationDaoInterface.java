package com.flipkart.dao;

import com.flipkart.bean.Notification;

import java.util.List;

public interface NotificationDaoInterface {
    public boolean sendNotification(Notification notification);

    public List<String> showNotifications(int studentId);

}
