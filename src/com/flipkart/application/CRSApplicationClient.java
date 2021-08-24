package com.flipkart.application;

import com.flipkart.bean.Student;
import com.flipkart.constants.Roles;

import java.util.Scanner;

public class CRSApplicationClient {
    static Scanner sc = new Scanner(System.in);
    static AdminClient adminClient = new AdminClient();
    static ProfessorClient professorClient = new ProfessorClient();
    static StudentClient studentClient = new StudentClient();

    public static void main(String[] args) {
        System.out.println("--------------------Welcome to Course Registration System--------------------");
        for (; ; ) {
            showMenu();
            int userInput = sc.nextInt();
            if (userInput == 3) {
                break;
            }
            if (userInput == 1) {
                // Login
                System.out.println("login");
                login();
            } else if (userInput == 2) {
                // Student Registration
                System.out.println("student registration");
                studentRegistration();
            } else {
                // Invalid userInput
                System.out.println("Invalid User Input");
            }
        }

    }

    static void showMenu() {
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Exit");
        System.out.println("Enter User Input");
    }

    static void login() {
        System.out.print("Enter EmailId: ");
        String emailId = sc.next();

        System.out.println("Enter Password");
        String password = sc.next();

        boolean verified = userInterface.validateUser(emailId, password);
        if (verified) {
            System.out.println("User Logged in Successfully");
            String role = userInterface.getRole(emailId);
            if (role == Roles.Admin) {
                adminClient.showMenu();
            } else if (role == Roles.Professor) {
                professorClient.showMenu();
            } else {
                studentClient.showMenu();
            }
        } else {
            System.out.println("Either EmailId or Password are Incorrect, Try Again");
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

        System.out.print("Enter Student Branch: ");
        String studentBranch = sc.next();

        Student student;
        student = studentInterface.register(studentName, studentEmailId, studentPassword, studentPhoneNo, studentBranch);
        System.out.println(studentName + " you are successfully registered, please wait for Admin's Approval");
    }
}
