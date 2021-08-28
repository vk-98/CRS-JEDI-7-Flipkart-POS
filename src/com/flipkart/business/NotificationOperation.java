package com.flipkart.business;

import com.flipkart.bean.Notification;

import java.util.List;

public interface NotificationOperation {

    public boolean sendNotification(String notificationContent);

    public List<Notification> getNotifications();
}
