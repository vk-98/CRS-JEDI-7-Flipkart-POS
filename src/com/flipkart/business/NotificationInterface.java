package com.flipkart.business;

import com.flipkart.bean.Notification;

public interface NotificationInterface {
    void notify(Notification notification);

    boolean notificationStatus(String notificationId);
}
