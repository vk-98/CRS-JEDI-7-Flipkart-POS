package com.flipkart.bean;

/**
 * @author JEDI-07
 * OptedCouse Bean
 */
public class OptedCourse extends Course {
    private int courseId;
    private boolean isPrimary;

    /**
     * Getter for CourseId
     *
     * @return CourseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Setter for CourseId
     *
     * @param courseId Unique Id of the Course
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Method to check if opted course is primary
     *
     * @return return true if opted course is primary
     */
    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
