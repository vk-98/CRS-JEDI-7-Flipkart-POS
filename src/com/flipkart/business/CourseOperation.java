package com.flipkart.business;

import java.util.Formatter;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import org.apache.log4j.Logger;

/**
 * @author JEDI-07
 * Implementation of Course Interface
 */
public class CourseOperation implements CourseInterface {
    private static Logger logger = Logger.getLogger(CourseOperation.class);
    AdminDaoInterface adminDaoInterface = new AdminDaoOperation();

    /**
     * method for viewing all the available courses in catalogue
     *
     * @return List of courses.
     */
    @Override
    public List<Course> getCourses() {
        return adminDaoInterface.viewCourses();
    }
}
