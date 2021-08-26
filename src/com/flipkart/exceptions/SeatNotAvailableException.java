package com.flipkart.exceptions;

public class SeatNotAvailableException extends Exception {
    private int courseId;

    public SeatNotAvailableException(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }
}
