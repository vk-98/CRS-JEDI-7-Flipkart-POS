package com.flipkart.bean;

/**
 * @author JEDI-07
 * Notification Bean
 */
public class Notification {
    private int notificationId;
    private int studentId;
    private String content;

    public Notification() {
    }

    /**
     * Parameterized Constructor
     *
     * @param content   Notification content
     * @param studentId Unique Id of a student
     */
    public Notification(String content, int studentId) {
        this.content = content;
        this.studentId = studentId;
    }

    /**
     * Getter for notification Id
     *
     * @return notification Id
     */
    public int getNotificationId() {
        return notificationId;
    }

    /**
     * Setter for NotificationId
     *
     * @param notificationId Unique Id for A Notification
     */
    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * Getter for Notification Content
     *
     * @return Notification Content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter for Notification Content
     *
     * @param content Notification Content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter for Student Id
     *
     * @return Student Id
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Setter for Student Id
     *
     * @param studentId Unique Id of the Student
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
