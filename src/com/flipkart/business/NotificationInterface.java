package com.flipkart.business;

import com.flipkart.bean.Notification;

public interface NotificationInterface {
    void showNotifications(int studentId);

    boolean notificationStatus(String notificationId);
}
