package com.flipkart.business;

import java.util.Formatter;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import org.apache.log4j.Logger;


public class CourseOperation implements CourseInterface {
    private static Logger logger = Logger.getLogger(CourseOperation.class);
    AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
    
    @Override
    public void viewCourses() {
        List<Course> courses = adminDaoInterface.viewCourses();
        if (courses == null || courses.size() == 0) {
            logger.info("No Available Courses");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%30s  %30s  %30s  %30s  %30s  %30s\n", "CourseID", "CourseName", "CourseDescription", "ProfessorID", "CourseFee", "StudentCount");
            for (Course c : courses) {
                fmt.format(
                        "%30s  %30s  %30s  %30s  %30s  %30s\n",
                        c.getCourseId(),
                        c.getCourseName(),
                        c.getCourseDescription(),
                        c.getProfessorId(),
                        c.getCourseFee(),
                        c.getStudentCount()
                );

            }
            System.out.println(fmt);
        }
    }


}
