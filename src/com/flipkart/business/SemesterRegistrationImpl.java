package com.flipkart.business;

//import com.flipkart.constants.Courses;

import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.HashSet;

public class SemesterRegistrationImpl implements SemesterRegistrationInterface{

    HashSet<String> primaryCourses= new HashSet<String>();
    HashSet<String> secondaryCourses= new HashSet<String>();

    @Override
    public boolean addPrimaryCourse(String studentId, String courseId) {
        if(primaryCourses.size() < 4)
        {
            primaryCourses.add(courseId);
            System.out.println("Successfully added primary course");
            return true;
        }

            System.out.println("Limit reached");

        return false;
    }

    @Override
    public boolean addSecondaryCourse(String studentId, String courseId) {
        if(secondaryCourses.size() < 2)
        {
            secondaryCourses.add(courseId);
            System.out.println("Successfully added secondary course");
            return true;
        }

            System.out.println("Limit reached");

        return false;
    }

    @Override
    public boolean dropCourse(String studentId, String courseId) {

        if(primaryCourses.contains(courseId))
        {
            primaryCourses.remove(courseId);
            System.out.println("Successfully removed primary course");
            return true;
        }
        if(secondaryCourses.contains(courseId))
        {
            secondaryCourses.remove(courseId);
            System.out.println("Successfully removed secondary course");
            return true;
        }

        System.out.println("Not found");
        return false;

    }



    @Override
    public void viewRegisteredCourses(String studentId) {
        System.out.println("** Registered courses **");


        if(primaryCourses.size()>0) {
            System.out.println("You have opted for " + primaryCourses.size() + " primary courses.");
            for (String course : primaryCourses) {
                System.out.println("-- " + course);
            }
        }

        if(secondaryCourses.size()>0) {
            System.out.println("You have opted for " + secondaryCourses.size() + " secondary courses.");
            for (String course : secondaryCourses) {
                System.out.println("-- " + course);
            }
        }

        if(primaryCourses.size()==0 && secondaryCourses.size()==0)
            System.out.println("No registered courses to show");

    }

    @Override
    public double calculateFee(String studentId) {
        double totalFee=0;
        for(Course course:CourseInterfaceImpl.courses)
        {
            if(primaryCourses.contains(course.getCourseId()))
            {
                totalFee+= course.getCourseFee();
            }

            if(secondaryCourses.contains(course.getCourseId()))
            {
                totalFee+= course.getCourseFee();
            }
        }
        return totalFee;
    }

    @Override
    public void submitCourseChoices(String studentId) {

        if(primaryCourses.size()!=4 || secondaryCourses.size()!=2)
        {
            System.out.println("Please register required courses");
            return;
        }
        System.out.println("Courses successfully registered");
    }

    @Override
    public boolean getRegistrationStatus(String studentId) {
        return false;
    }

    @Override
    public void setRegistrationStatus(String studentId, boolean status) {

    }
}
