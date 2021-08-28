package com.flipkart.exceptions;

public class StudentNotRegisteredException extends Exception{
    private String studentName;

    /**
     * Parameterized Constructor
     * @param studentName: name of the student
     */
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