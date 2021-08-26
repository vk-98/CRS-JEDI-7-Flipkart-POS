package com.flipkart.exceptions;

/**
 * Exception to check if course is available in catalog
 */
public class CourseNotFoundException extends Exception {
    private int courseId;

    public CourseNotFoundException(int courseCode) {
        this.courseId = courseCode;
    }

    /**
     * Getter function for course code
     * @return
     */
    public int getCourseCode() {
        return courseId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Course with courseId: " + courseId + " not found.";
    }
}
