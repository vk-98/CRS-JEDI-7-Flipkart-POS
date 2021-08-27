package com.flipkart.dao;

import com.flipkart.bean.Grades;
import com.flipkart.exception.GradeNotAddedException;

public interface GradeDaoInterface {
    public boolean createGrade(int courseId, int studentId, int gpa) throws GradeNotAddedException;
}
