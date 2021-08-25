package com.flipkart.application;

import com.flipkart.bean.Student;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentInterfaceImpl;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserInterfaceImpl;
import com.flipkart.constants.Roles;

import java.util.Scanner;

public class CRSApplicationClient {
    static Scanner sc = new Scanner(System.in);
    static AdminClient adminClient = new AdminClient();
    static ProfessorClient professorClient = new ProfessorClient();
    static StudentClient studentClient = new StudentClient();
    static StudentInterface studentInterface = new StudentInterfaceImpl();
    static UserInterface userInterface = new UserInterfaceImpl();

    public static void main(String[] args) {
        System.out.println("#######################################################################################");
        System.out.println("#------------------------Welcome to Course Registration System------------------------#");
        System.out.println("#######################################################################################");

        while (true) {
            showMenu();
            int userInput = sc.nextInt();
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

    static void login() {
        System.out.print("Enter EmailId: ");
        String emailId = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        boolean verified = userInterface.validateUser(emailId, password);

        //TODO replace dummy id
        String studentId = String.valueOf(1);
        if (verified) {
            System.out.println("User Logged in Successfully");
            String role = userInterface.getRole(emailId);
            if (role == Roles.Admin) {
                adminClient.showMenu();
            } else if (role == Roles.Professor) {
                professorClient.showMenu();
            } else {
                studentClient.showMenu(studentId);
            }
        }
    }

    static void studentRegistration() {
        System.out.print("Enter Student Name: ");
        String studentName = sc.next();

        System.out.print("Enter Student EmailId: ");
        String studentEmailId = sc.next();

        System.out.print("Enter Student Password: ");
        String studentPassword = sc.next();

        System.out.print("Enter Student Phone number: ");
        String studentPhoneNo = sc.next();

        Student student;
        student = studentInterface.register(studentName, studentEmailId, studentPassword, studentPhoneNo, null);

        if (student == null) {
            return;
        }

        System.out.println(studentName + "you are successfully registered with CRS, your registered details are as follows");
        System.out.println("Name: " + studentName);
        System.out.println("EmailId: " + studentEmailId);
        System.out.println("PhoneNo: " + studentPhoneNo);
//        System.out.println(studentName + " you are successfully registered, please wait for Admin's Approval");
    }
}
