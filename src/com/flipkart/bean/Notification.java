package com.flipkart.bean;

public class Notification {
    private String notificationId;
    private boolean isNotificationSent;

    public Notification() {
    }

    public Notification(String notificationId, boolean isNotificationSent, String content, String studentId) {
        this.notificationId = notificationId;
        this.isNotificationSent = isNotificationSent;
        this.content = content;
        this.studentId = studentId;
    }

    private String content;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public boolean isNotificationSent() {
        return isNotificationSent;
    }

    public void setNotificationSent(boolean notificationSent) {
        isNotificationSent = notificationSent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    private String studentId;

}
