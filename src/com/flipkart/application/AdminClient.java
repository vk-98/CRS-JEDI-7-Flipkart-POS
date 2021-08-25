package com.flipkart.application;

import java.util.Scanner;

import com.flipkart.bean.Professor;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminInterfaceImpl;
import com.flipkart.business.UserInterfaceImpl;

import javax.management.relation.Role;

public class AdminClient {

    Scanner sc = new Scanner(System.in);
    AdminInterface admin = new AdminInterfaceImpl();

    public void showMenu() {

        boolean menuBreakFlag = false;

        while (!menuBreakFlag) {
            showAdminMenu();
            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    removeCourse();
                    break;
                case 3:
                    addProfessor();
                    break;
                case 4:
                    approveStudentRequest();
                    break;
                case 5:
                    generateResult();
                    break;
                case 6: {
                    menuBreakFlag = true;
                    UserInterfaceImpl.logout();
                    break;
                }
                default:
                    menuBreakFlag = true;

            }

        }
    }

    static void showAdminMenu() {
        System.out.println("`Admin Menu`");
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        System.out.println("3. Add Professor");
        System.out.println("4. Approve Student");
        System.out.println("5. Generate Grades");
        System.out.println("6. Logout");
        System.out.println("Enter Your Preference :");
    }

    public void addCourse() {
        System.out.println("Enter details of the course to be added :");

        System.out.println("Course Id - ");
        String courseId = sc.next();

        System.out.println("Course Name - ");
        String courseName = sc.next();

        System.out.println("Student Count - ");
        int studentCount = sc.nextInt();

        System.out.println("Course Fee - ");
        double courseFee = sc.nextDouble();

        admin.addCourse(courseId, courseName, studentCount, courseFee);
    }


    public void removeCourse() {
        System.out.println("Enter ID of the course to be deleted :");

        System.out.println("Course Id - ");
        String courseId = sc.next();

        admin.removeCourse(courseId);

    }


    public void addProfessor() {
        System.out.println("Enter details of the Professor to be added :");

        Professor professor = new Professor();

        System.out.println("Enter Name");
        String professorName = sc.next();
        professor.setUserName(professorName);

        System.out.println("Enter Email Id:");
        String userId = sc.next();
        professor.setUserEmailId(userId);

        System.out.println("Enter Password:");
        String password = sc.next();
        professor.setUserPassword(password);

        System.out.println("Enter Phone No:");
        String phoneNo = sc.next();
        professor.setPhoneNo(password);

        professor.setRole("Professor");

        System.out.println("Enter Department:");
        String department = sc.next();
        professor.setDepartment(department);

        System.out.println("Enter Designation:");
        String designation = sc.next();
        professor.setDesignation(designation);

        System.out.println("Enter Professor Id - ");
        String professorId = sc.next();
        professor.setProfessorId(professorId);


        admin.addProfessor(professor);
    }

    public void approveStudentRequest() {
        System.out.println("Enter details of the student to be approved :");

        System.out.println("Student Id - ");
        String studentId = sc.next();

        admin.approveStudentRequest(studentId);
    }

    public void generateResult() {
        System.out.println("Generating results .... ..... .....");

    }


}