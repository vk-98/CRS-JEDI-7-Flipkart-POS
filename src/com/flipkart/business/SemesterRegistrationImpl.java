package com.flipkart.business;

//import com.flipkart.constants.Courses;

import com.flipkart.bean.Course;
import com.flipkart.dao.SemesterRegistrationDaoInterface;
import com.flipkart.dao.SemesterRegistrationDaoOperation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class SemesterRegistrationImpl implements SemesterRegistrationInterface {

    HashSet<String> primaryCourses = new HashSet<String>();
    HashSet<String> secondaryCourses = new HashSet<String>();

    SemesterRegistrationDaoInterface semesterRegistrationDaoInterface= new SemesterRegistrationDaoOperation();

    @Override
    public boolean addPrimaryCourse(int studentId, int courseId) {

        if(semesterRegistrationDaoInterface.addCourse(courseId,studentId,0))
        {
            System.out.println("Successfully added primary course");
            return true;
        }


        System.out.println("Limit reached");

        return false;
    }

    @Override
    public boolean addSecondaryCourse(int studentId, int courseId) {
        if(semesterRegistrationDaoInterface.addCourse(courseId,studentId,1))
        {
            System.out.println("Successfully added secondary course");
            return true;
        }


        System.out.println("Limit reached");

        return false;
    }

    @Override
    public boolean dropCourse(int studentId, int courseId) {

        if(semesterRegistrationDaoInterface.dropCourse(courseId,studentId))
        {
            System.out.println("Course Deleted");
            return true;
        }

        System.out.println("Not found");
        return false;

    }


    @Override
    public void viewRegisteredCourses(int studentId) {
        System.out.println("** Registered courses **");
        List<Course> courses = semesterRegistrationDaoInterface.viewRegisteredCourses(studentId);

        if(courses.size()==0)
        {
            System.out.println("### No registered courses to show");
            return;
        }

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

    @Override
    public double calculateFee(int studentId) throws SQLException {
        double totalFee = 0;
        totalFee= semesterRegistrationDaoInterface.calculateFee(studentId);
        return totalFee;
    }

    @Override
    public boolean submitCourseChoices(int studentId) {
        viewRegisteredCourses(studentId);
        System.out.println("Do you want to proceed?  ");
        System.out.println("Enter 1 for yes");
        System.out.println("Enter 2 for no");

        Scanner sc= new Scanner(System.in);
        int x= sc.nextInt();

        if(x==1)
        {
           if(semesterRegistrationDaoInterface.submitChoices(studentId)) {
               System.out.println("Courses submitted successfully");return true;
           }
            System.out.println("Invalid selection");
        }
        return false;
    }

    @Override
    public boolean getRegistrationStatus(int studentId) {
        return false;
    }

    @Override
    public void setRegistrationStatus(int studentId, boolean status) {

    }
}
