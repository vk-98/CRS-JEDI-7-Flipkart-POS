package com.flipkart.bean;

/**
 * @author JEDI-07
 * OptedCouse Bean
 */
public class OptedCourse extends Course{
    private int courseId;
    private boolean isPrimary;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
