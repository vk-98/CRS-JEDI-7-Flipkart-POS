package com.flipkart.business;

//TODO: connect with DB and update the methods

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.util.ArrayList;

public class AdminInterfaceImpl implements AdminInterface {
    ArrayList<Course> courses= CourseInterfaceImpl.courses;
    static Admin admin = new Admin("b","admin","b","Admin","b","b","b");
    @Override
    public boolean addCourse(String courseId, String courseName, int studentCount, double courseFee) {

        Course course = new Course(courseId, "random", courseName,  courseFee, studentCount );
        courses.add(course);
        System.out.println("Course added !");
        return true;

    }

    @Override
    public boolean removeCourse(String courseId) {

        for(Course course:courses)
        {
            if(course.getCourseId()==courseId)
            {
                courses.remove(course);
                System.out.println("Course removed !");
                return true;
            }
        }
        System.out.println("Course not found removed !");

        return false;
    }

    @Override
    public boolean approveStudentRequest(String studentId) {
        return false;
    }

    @Override
    public boolean addProfessor(Professor professor) {
        //add to professor list
        System.out.println("Professor added successfully");
        return true;
    }

    @Override
    public void generateResult() {
       //TODO: after student db
    }
}