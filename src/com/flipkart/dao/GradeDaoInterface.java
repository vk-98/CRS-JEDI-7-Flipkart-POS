package com.flipkart.dao;

import com.flipkart.bean.Grades;

public interface GradeDaoInterface {
    public boolean createGrade(int courseId, int studentId, int gpa);
}
