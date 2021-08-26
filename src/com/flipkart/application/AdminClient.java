package com.flipkart.application;

import java.util.Scanner;

import com.flipkart.bean.Professor;
import com.flipkart.business.*;

import javax.management.relation.Role;

public class AdminClient {

    Scanner sc = new Scanner(System.in);
    AdminInterface adminInterface = new AdminInterfaceImpl();
    CourseInterface courseInterface = new CourseInterfaceImpl();

    public void showMenu() {

        boolean menuBreakFlag = false;

        while (!menuBreakFlag) {
            showAdminMenu();
            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    courseInterface.viewCourses();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    addProfessor();
                    break;
                case 5:
                    adminInterface.viewProfessors();
                    break;
                case  6:
                    adminInterface.listAdmissionRequests();
                    break;
                case 7:
                    approveStudentRequest();
                    break;
                case 8:
                    menuBreakFlag = true;
                    UserInterfaceImpl.logout();
                    break;
                default:
                    System.out.println("Invalid User Input");
            }

        }
    }

    static void showAdminMenu() {
        System.out.println("*********************************************************************************");
        System.out.println("********************************* Admin Menu ************************************");
        System.out.println("*********************************************************************************");
        System.out.println("1. View Courses");
        System.out.println("2. Add Course");
        System.out.println("3. Remove Course");
        System.out.println("4. Add Professor");
        System.out.println("5. List Professors");
        System.out.println("6. List Admission Requests");
        System.out.println("7. Approve Student");
        System.out.println("8. Logout");
        System.out.print("Enter User Input: ");
    }

    public void addCourse() {
        System.out.println("Enter details of the course to be added :");

        System.out.print("Course Name: ");
        String courseName = sc.next();

        System.out.print("Course Description: ");
        String description = sc.next();

        System.out.print("Course Fee: ");
        double courseFee = sc.nextDouble();

        adminInterface.addCourse(courseName, description, courseFee);
    }


    public void removeCourse() {
        System.out.print("Enter ID of the course to be deleted: ");
        int courseId = sc.nextInt();

        adminInterface.removeCourse(courseId);

    }


    public void addProfessor() {
        System.out.println("Enter details of the Professor to be added: ");

        System.out.print("Enter Name - ");
        String professorName = sc.next();

        System.out.print("Enter Email Id - ");
        String emailId = sc.next();

        System.out.print("Enter Password - ");
        String password = sc.next();

        System.out.print("Enter Phone No - ");
        String phoneNo = sc.next();

        System.out.print("Enter Department - ");
        String department = sc.next();

        System.out.print("Enter Designation - ");
        String designation = sc.next();

        adminInterface.addProfessor(professorName, emailId, password, phoneNo, department, designation);
    }

    public void approveStudentRequest() {
        System.out.print("Enter Student Id - ");
        int studentId = sc.nextInt();

        adminInterface.approveStudentRequest(studentId);
    }
}