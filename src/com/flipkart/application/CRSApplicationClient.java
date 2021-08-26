package com.flipkart.application;

import com.flipkart.business.UserInterface;
import com.flipkart.business.UserInterfaceImpl;
import com.flipkart.constants.Roles;

import java.util.Scanner;
public class CRSApplicationClient {
    static Scanner sc = new Scanner(System.in);

    static ProfessorClient professorClient = new ProfessorClient();
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
                    //studentRegistration();
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

    /**
     * After logging in as verified user this function redirects to respective client for the display of Menus.
     * For Example, If the Logged in User is a Professor, then We redirect this request to ProfessorClient
     */
    static void login() {
        // Taking credentials as input.
        System.out.print("Enter EmailId: ");
        String emailId = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        boolean verified = userInterface.validateUser(emailId, password);

        String studentId = "1";
        if (verified) {
            System.out.println("User Logged in Successfully");
            String role = UserInterfaceImpl.user.getRole();
            System.out.println(role);
            if (role == Roles.Admin) {

            } else if (role.equals(Roles.Professor) ) {
                professorClient.showMenu(emailId); //emailId is passed in order to retrieve corresponding ProfessorId
            } else {

            }
        } else {
            System.out.println("Email or Password is incorrect, Try Again");
        }
    }

}
