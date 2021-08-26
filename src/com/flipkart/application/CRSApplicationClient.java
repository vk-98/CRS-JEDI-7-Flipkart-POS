package com.flipkart.application;

import com.flipkart.bean.Student;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentInterfaceImpl;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserInterfaceImpl;
import com.flipkart.constants.Roles;
import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;
import com.flipkart.exceptions.StudentNotRegisteredException;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * This class is used as the main entry point of the application
 * login and register are displayed in the main menu
 *
 */

public class CRSApplicationClient {
    static Scanner sc = new Scanner(System.in);
    static AdminClient adminClient = new AdminClient();
    static ProfessorClient professorClient = new ProfessorClient();
    static StudentClient studentClient = new StudentClient();
    static StudentInterface studentInterface = new StudentInterfaceImpl();
    static UserInterface userInterface = new UserInterfaceImpl();

    public static void main(String[] args) throws SQLException, StudentNotRegisteredException, CourseCountException, NoRegisteredCourseException, SeatNotAvailableException {
        System.out.println("#######################################################################################");
        System.out.println("#------------------------Welcome to Course Registration System------------------------#");
        System.out.println("#######################################################################################");

        //until user exists the application
        while (true) {
            // Display the menu
            showMenu();

            int userInput = sc.nextInt();
            if (userInput == 3) {
                break;
            }
            switch (userInput) {
                case 1:
                    //Login into the Application
                    login();
                    break;

                case 2:
                    // Student Registration
                    studentRegistration();
                    break;
                default:
                    System.out.println("Invalid User Input");
            }

        }

    }

    /**
     * Method to Display Main Menu
     */
    static void showMenu() {
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Exit");
        System.out.print("Enter User Input: ");
    }

    /**
     * Method for Login functionality
     */
    static void login() throws SQLException, CourseCountException, NoRegisteredCourseException, SeatNotAvailableException {
        // Taking credentials as input.
        System.out.print("Enter EmailId: ");
        String emailId = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        boolean verified = userInterface.validateUser(emailId, password);

        // if the user is valid, display the respective menus based on their role
        if (verified) {

            String role = UserInterfaceImpl.user.getRole();

            if (!Roles.Student.equals(role)) {
                System.out.println("User Logged in Successfully");
            }
            //If Admin is the user display Admin Menu
            if (Roles.Admin.equals(role)) {
                adminClient.showMenu();
            }
            //If Professor is the user display Professor Menu
            else if (Roles.Professor.equals(role)) {
                professorClient.showMenu();
            }
            //If Student is the user display Student Menu
            else if (Roles.Student.equals(role)) {
                studentClient.showMenu();
            }
            //If none of them is the user
            else {
                System.out.println("UnIdentified User");
            }
        } else {
            System.out.println("Email or Password is incorrect, Try Again");
        }
    }

    /**
     * Method for registering Students
     * approval request from the Admin will be still pending
     */
    static void studentRegistration() throws StudentNotRegisteredException {
        // Taking user input
        System.out.print("Enter Student Name: ");
        String studentName = sc.next();

        System.out.print("Enter Student EmailId: ");
        String studentEmailId = sc.next();

        System.out.print("Enter Student Password: ");
        String studentPassword = sc.next();

        System.out.print("Enter Student Phone number: ");
        String studentPhoneNo = sc.next();

        Student student;
        student = studentInterface.register(studentName, studentEmailId, studentPassword, studentPhoneNo);

        if (student == null) {
            System.out.println("Something went wrong, Try Again");
            return;
        }
        System.out.println(studentName + " you are successfully registered, please wait for Admin's Approval");
    }
}
