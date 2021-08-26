package com.flipkart.bean;

/**
 * Opted Course Class
 */
public class OptedCourse {
    private int courseId;
    private boolean isPrimary;

    public OptedCourse(int courseId, boolean isPrimary) {
        this.courseId = courseId;
        this.isPrimary = isPrimary;
    }

    /**
     * Method to get the id of the Course opted by a student
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Method to set the id of the Course opted by a student
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Method to check if the opted course is primary or not
     */
    public boolean isPrimary() {
        return isPrimary;
    }

    /**
     * Method to set the opted course as the primary course
     */
    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
