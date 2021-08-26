package com.flipkart.exception;

public class StudentNotRegisteredException extends Exception{
    private String studentName;

    public StudentNotRegisteredException(String studentName)
    {
        this.studentName=studentName;
    }

    /**
     * getter function for studentName
     * @return
     */
    public String getStudentName()
    {
        return studentName;
    }
}
