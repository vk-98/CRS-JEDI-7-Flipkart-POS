package com.flipkart.application;

import java.util.Scanner;

import com.flipkart.business.*;
import org.apache.log4j.Logger;

/**
 * @author JEDI-07
 * Admin Client
 */
public class AdminClient {
    private static Logger logger = Logger.getLogger(AdminClient.class);
    Scanner sc = new Scanner(System.in);
    AdminInterface adminInterface = new AdminInterfaceImpl();
    CourseInterface courseInterface = new CourseInterfaceImpl();
    UserInterface userInterface = new UserInterfaceImpl();

    /**
     * method for admin menu
     */
    public void showMenu() {
        logger.info("User Logged in Successfully");

        boolean exit = false;

        while (!exit) {
            showAdminMenu();

            int userInput = sc.nextInt();
            sc.nextLine();

            switch (userInput) {
                case 1:
                    handleViewCourses();
                    break;
                case 2:
                    handleAddCourse();
                    break;
                case 3:
                    handleRemoveCourse();
                    break;
                case 4:
                    handleAddProfessor();
                    break;
                case 5:
                    handleViewProfessor();
                    break;
                case 6:
                    handelViewAdmissionRequests();
                    break;
                case 7:
                    handleApproveStudentAdmissionRequest();
                    break;
                case 8:
                    handleUpdatePassword();
                    break;
                case 9:
                    handleLogout();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid User Input");
            }

        }
    }

    /**
     * Method for displaying admin action menu
     */
    private void showAdminMenu() {
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
        System.out.println("8. Update Password");
        System.out.println("9. Logout");
        System.out.print("Enter User Input: ");
    }

    /**
     * method for view courses
     */
    private void handleViewCourses() {
        courseInterface.viewCourses();
    }

    /**
     * method for adding course
     */
    private void handleAddCourse() {

        System.out.println("Enter details of the course to be added :");

        System.out.print("Course Name: ");
        String courseName = sc.nextLine();

        System.out.print("Course Description: ");
        String description = sc.nextLine();

        System.out.print("Course Fee: ");
        double courseFee = sc.nextDouble();

        adminInterface.addCourse(courseName, description, courseFee);
    }

    /**
     * method for removing course
     */
    private void handleRemoveCourse() {
        System.out.print("Enter ID of the course to be deleted: ");
        int courseId = sc.nextInt();

        adminInterface.removeCourse(courseId);
    }

    /**
     * method for adding professor
     */
    private void handleAddProfessor() {
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

    /**
     * method for viewing all available course
     */
    private void handleViewProfessor() {
        adminInterface.viewProfessors();
    }

    /**
     * method for viewing all available admission requests.
     */
    private void handelViewAdmissionRequests() {
        adminInterface.listAdmissionRequests();
    }

    /**
     * method for updating password.
     */
    private void handleUpdatePassword() {
        System.out.print("Enter New Password: ");
        String newPassword = sc.next();
        userInterface.updateUserPassword(newPassword);
    }

    /**
     * method for logging out.
     */
    private void handleLogout() {
        UserInterfaceImpl.logout();
    }

    /**
     * method for approving student admission request.
     */
    private void handleApproveStudentAdmissionRequest() {
        System.out.print("Enter Student Id - ");
        int studentId = sc.nextInt();

        adminInterface.approveStudentRequest(studentId);
    }
}