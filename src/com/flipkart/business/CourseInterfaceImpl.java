package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;

public class CourseInterfaceImpl implements CourseInterface {
    AdminDaoInterface adminDaoInterface = new AdminDaoOperation();

    @Override
    public void viewCourses() {
        List<Course> courses = adminDaoInterface.viewCourses();
        if (courses == null || courses.size() == 0) {
            System.out.println("No Available Courses");
        } else {
            System.out.println("Course Id | Course Name | Course Description | ProfessorID | CourseFee | StudentCount ");
            for (Course c : courses) {
                System.out.println(
                        c.getCourseId()
                                + " | " + c.getCourseName()
                                + " | " + c.getCourseDescription()
                                + " | " + c.getProfessorId()
                                + " | " + c.getCourseFee()
                                + " | " + c.getStudentCount()
                );
            }
        }
    }


}
