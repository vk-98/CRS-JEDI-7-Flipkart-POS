package com.flipkart.exceptions;

/**
 * @author JEDI-07
 * CourseNotSelectedException
 */
public class CourseNotSelectedExcpetion extends Exception {
    private int courseId;

    /**
     * Parameterized Constructor
     *
     * @param courseId Unique Id of the Course
     */
    public CourseNotSelectedExcpetion(int courseId) {
        this.courseId = courseId;
    }

    /**
     * Method for getting Exception Message
     *
     * @return returns Message
     */
    @Override
    public String getMessage() {
        return "Course with Course ID: " + courseId + " not selected by the professor.";
    }
}
