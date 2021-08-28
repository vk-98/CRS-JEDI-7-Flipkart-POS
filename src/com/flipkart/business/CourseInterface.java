package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

/**
 * @author JEDI-07
 * Course Interface
 */
public interface CourseInterface {
    /**
     * method for viewing all the available courses in catalogue
     *
     * @return List of courses.
     */
    List<Course> getCourses();
}
