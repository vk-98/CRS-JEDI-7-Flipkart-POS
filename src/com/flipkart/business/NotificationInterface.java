package com.flipkart.business;

import com.flipkart.bean.Notification;

import java.util.List;

public interface NotificationInterface {

    public boolean sendNotification(String notificationContent);

    public List<Notification> getNotifications();
}
