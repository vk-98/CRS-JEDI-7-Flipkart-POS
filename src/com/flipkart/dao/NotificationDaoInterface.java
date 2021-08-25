package com.flipkart.dao;

import com.flipkart.bean.Notification;

public interface NotificationDaoInterface {
    public int sendNotification(Notification type);

}
