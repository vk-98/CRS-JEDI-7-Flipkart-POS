package com.flipkart.bean;

public class Notification {
    private int notificationId;
    private int studentId;
    private String content;

    public Notification() {
    }

    /**
     * Parameterized Constructor
     * @param content: notification content
     * @param studentId: student id
     */
    public Notification( String content, int studentId) {
        this.content = content;
        this.studentId = studentId;
    }


    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }



}
