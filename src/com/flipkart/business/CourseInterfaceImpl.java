package com.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.dao.SemesterRegistrationDaoInterface;
import com.flipkart.dao.SemesterRegistrationDaoOperation;

public class CourseInterfaceImpl implements CourseInterface {

    SemesterRegistrationDaoInterface semesterRegistrationDaoInterface= new SemesterRegistrationDaoOperation();
    @Override
    public void viewCourses() {
        List<Course> courses= semesterRegistrationDaoInterface.viewCourses();
        System.out.println("CourseId | ProfessorId | CourseName | CourseFee | Student Count");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(
                    courses.get(i).getCourseId() +
                            " | " +
                            courses.get(i).getProfessorId()
                            + " | " +
                            courses.get(i).getCourseName() +
                            " | " +
                            courses.get(i).getCourseFee() +
                            " | " +
                            courses.get(i).getStudentCount()
            );
        }
    }
}
