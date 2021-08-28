package com.flipkart.application;

import com.flipkart.bean.Student;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;
import com.flipkart.constants.Roles;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * @author JEDI-07
 * Entry Point of CRS APPLICATION
 */
public class CRSApplicationClient {

    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    static Scanner sc = new Scanner(System.in);
    static AdminClient adminClient = new AdminClient();
    static ProfessorClient professorClient = new ProfessorClient();
    static StudentClient studentClient = new StudentClient();
    static StudentInterface studentInterface = new StudentOperation();
    static UserInterface userInterface = new UserOperation();

    public static void main(String[] args) {
        System.out.println("#######################################################################################");
        System.out.println("#------------------------Welcome to Course Registration System------------------------#");
        System.out.println("#######################################################################################");

        boolean exit = false;
        while (!exit) {

            showMenu();

            int userInput = sc.nextInt();
            sc.nextLine();

            switch (userInput) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleStudentRegistration();
                    break;
                case 3:
                    handleExit();
                    exit = true;
                    break;
                default:
                    logger.warn("Invalid User Input");
            }
        }
    }

    /**
     * method for displaying main menu
     */
    static void showMenu() {
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Exit");
        System.out.print("Enter User Input: ");
    }

    /**
     * method for logging in
     */
    static void handleLogin() {

        System.out.print("Enter EmailId: ");
        String emailId = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        boolean isAuthenticated = userInterface.validateUser(emailId, password);

        if (isAuthenticated) {
            String role = UserOperation.user.getRole();

            if (Roles.Admin.equals(role)) {
                adminClient.showMenu();
            } else if (Roles.Professor.equals(role)) {
                professorClient.showMenu();
            } else if (Roles.Student.equals(role)) {
                studentClient.showMenu();
            } else {
                logger.warn("Unidentified User");
            }
        } else {
            logger.info("Email or Password is incorrect, Try Again");
        }
    }

    /**
     * method initiating student registration
     */
    static void handleStudentRegistration() {

        System.out.print("Enter Student Name: ");
        String studentName = sc.nextLine();

        System.out.print("Enter Student EmailId: ");
        String studentEmailId = sc.nextLine();

        System.out.print("Enter Student Password: ");
        String studentPassword = sc.nextLine();

        System.out.print("Enter Student Phone number: ");
        String studentPhoneNo = sc.nextLine();

        Student student;
        student = studentInterface.register(studentName, studentEmailId, studentPassword, studentPhoneNo);

        if (student == null) {
            logger.info("Something went wrong, Try Again");
            return;
        }
        logger.info(studentName + " you are successfully registered, please wait for Admin's Approval");
    }

    /**
     * method for handling exit from application
     */
    static void handleExit() {
        System.out.println("Thanks for your visit");
    }
}


