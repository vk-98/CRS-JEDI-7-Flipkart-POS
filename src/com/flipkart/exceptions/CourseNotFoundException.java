package com.flipkart.exceptions;

/**
 * @author JEDI-07
 * CourseNotFoundException
 */
public class CourseNotFoundException extends Exception {
    private int courseId;

    /**
     * Parameterized Constructor
     *
     * @param courseCode Unique Id of the Course
     */
    public CourseNotFoundException(int courseCode) {
        this.courseId = courseCode;
    }

    /**
     * Getter for course Id
     *
     * @return returns courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * Method for getting Exception Message
     *
     * @return returns Message
     */
    @Override
    public String getMessage() {
        return "Course with courseId: " + courseId + " not found.";
    }
}
