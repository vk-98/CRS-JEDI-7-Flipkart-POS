package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;

public interface CourseDaoInterface {
    public List<Course> getCoursesByProfessorId(int professorId);
    public List<Integer> getStudentIdsByProfessorId(int professorId);
    public int getProfessorIdByCourseId(int courseId) throws CourseNotFoundException;
    void setProfessorToCourse(int professorId, int courseId);
}
