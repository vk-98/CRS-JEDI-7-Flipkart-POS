package com.flipkart.exception;
//GradeNotAdded By Professor Exception

public class GradeNotAddedException extends Exception{

    private int studentId;

    /**
     * Constructor
     * @param studentId
     */
    public GradeNotAddedException(int studentId)
    {
        this.studentId=studentId;
    }

    /**
     * Getter function for studentId
     * @return
     */
    public int getStudentId()
    {
        return studentId;
    }
    public String getMessage()
    {
        return "Grade Not added, check studentId and courseId ";
    }



}
