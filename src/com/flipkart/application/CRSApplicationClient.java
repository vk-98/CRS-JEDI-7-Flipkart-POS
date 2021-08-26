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

        while (true) {
            showMenu();
            int userInput = sc.nextInt();
            sc.nextLine();

            if (userInput == 3) {
                break;
            }
            switch (userInput) {
                case 1:
                    login();
                    break;
                case 2:
                    studentRegistration();
                    break;
                default:
                    System.out.println("Invalid User Input");
            }

        }

    }

    static void showMenu() {
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Exit");
        System.out.print("Enter User Input: ");
    }

    static void login() throws SQLException, CourseCountException, NoRegisteredCourseException, SeatNotAvailableException {
        // Taking credentials as input.
        System.out.print("Enter EmailId: ");
        String emailId = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        boolean verified = userInterface.validateUser(emailId, password);


        if (verified) {

            String role = UserInterfaceImpl.user.getRole();

            if (Roles.Admin.equals(role)) {
                adminClient.showMenu();
            } else if (Roles.Professor.equals(role)) {
                professorClient.showMenu();
            } else if (Roles.Student.equals(role)) {
                studentClient.showMenu();
            } else {
                System.out.println("UnIdentified User");
            }
        } else {
            System.out.println("Email or Password is incorrect, Try Again");
        }
    }

    static void studentRegistration() throws StudentNotRegisteredException {
        // Taking user input
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
            System.out.println("Something went wrong, Try Again");
            return;
        }
        System.out.println(studentName + " you are successfully registered, please wait for Admin's Approval");
    }
}
