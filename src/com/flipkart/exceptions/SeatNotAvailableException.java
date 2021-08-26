package com.flipkart.exceptions;

/**
 * Exception to check if seats are available for course registration
 */
public class SeatNotAvailableException extends Exception {
    private int courseId;

    /**
     * Constructor
     * @param courseId
     */
    public SeatNotAvailableException(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Getter function for CourseId
     * @return
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return  "Seats are not available in : " + courseId;
    }
}
