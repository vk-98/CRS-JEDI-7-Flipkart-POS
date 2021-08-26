package com.flipkart.bean;

public class OptedCourse {
    private int courseId;
    private boolean isPrimary;

    public OptedCourse(int courseId, boolean isPrimary) {
        this.courseId = courseId;
        this.isPrimary = isPrimary;
    }

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
