package com.flipkart.bean;

/**
 * Notification Class
 */
public class Notification {
    private int notificationId;
    private boolean isNotificationSent;
    private int studentId;
    private String content;

    public Notification() {
    }

    public Notification( boolean isNotificationSent, String content, int studentId) {
        this.isNotificationSent = isNotificationSent;
        this.content = content;
        this.studentId = studentId;
    }

    /**
     * Method to get the NotificationId
     */
    public int getNotificationId() {
        return notificationId;
    }

    /**
     * Method to set the NotificationId
     */
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Method to check if notification is sent successfully
     */
    public boolean isNotificationSent() {
        return isNotificationSent;
    }

    /**
     * Method to set the Notification Status
     */
    public void setNotificationSent(boolean notificationSent) {
        isNotificationSent = notificationSent;
    }

    /**
     * Method to retrieve the Notification message
     */
    public String getContent() {
        return content;
    }

    /**
     * Method to set the notification message
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Method to get the id of the Student for whom the notification is sent
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Method to set the id of the Student for whom the notification will be sent
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

}
