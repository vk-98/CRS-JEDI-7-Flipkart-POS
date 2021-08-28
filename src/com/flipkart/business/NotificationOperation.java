package com.flipkart.business;

import com.flipkart.bean.Notification;

import java.util.List;

/**
 * @author JEDI-07
 * Admin Client
 */
public interface NotificationOperation {

    public boolean sendNotification(String notificationContent);

    public List<Notification> getNotifications();
}
