package com.flipkart.exceptions;

public class RequiredCoursesSelectedException extends Exception{
    private String msg="Please Select 4 Primary and 2 Secondary Courses.";

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
