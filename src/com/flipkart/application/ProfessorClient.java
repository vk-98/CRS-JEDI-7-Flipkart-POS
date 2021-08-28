package com.flipkart.application;

import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * @author JEDI-07
 * Professor Client
 */
public class ProfessorClient {

    private static Logger logger = Logger.getLogger(ProfessorClient.class);
    static Scanner sc = new Scanner(System.in);
    ProfessorInterface professorInterface = new ProfessorOperation();
    UserInterface userInterface = new UserOperation();

    /**
     * method for professor menus
     */
    public void showMenu() {
        logger.info("User Logged in Successfully");
        professorInterface.getProfessor();

        boolean exit = false;

        while (!exit) {
            showMenuItems();

            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    handleViewAvailableCourses();
                    break;
                case 2:
                    handleSelectCourse();
                    break;
                case 3:
                    handleDeselectCourse();
                    break;
                case 4:
                    handleViewSelectedCourse();
                    break;
                case 5:
                    handleViewEnrolledStudents();
                    break;
                case 6:
                    handleStudentGrading();
                    break;
                case 7:
                    handleUpdatePassword();
                    break;
                case 8:
                    handleLogout();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid User Input");
            }
        }
    }

    /**
     * method for displaying professor action menus
     */
    private void showMenuItems() {
        System.out.println("1. View Available Course");
        System.out.println("2. Select Course");
        System.out.println("3. Deselect Course");
        System.out.println("4. View Selected Course");
        System.out.println("5. View Enrolled Students");
        System.out.println("6. Add Grade");
        System.out.println("7. Update Password");
        System.out.println("8. Logout");
        System.out.print("Enter User Input: ");
    }

    /**
     * method for viewing available course to select.
     */
    private void handleViewAvailableCourses() {
        professorInterface.viewAvailableCourses();
    }

    /**
     * method for viewing enrolled students in a course
     */
    private void handleViewEnrolledStudents() {
        System.out.print("Enter the courseId: ");
        int courseId = sc.nextInt();
        professorInterface.viewEnrolledStudents(courseId);
    }


    /**
     * method for deselecting a course
     */
    private void handleDeselectCourse() {
        System.out.print("Input courseId you like to Deselect : ");
        int courseId = sc.nextInt();
        professorInterface.deselectCourse(courseId);
    }

    /**
     * method for selecting a course
     */
    private void handleSelectCourse() {
        System.out.print("Input courseId you like to select : ");
        int courseId = sc.nextInt();
        professorInterface.selectCourse(courseId);
    }

    /**
     * method for viewing selected course
     */
    private void handleViewSelectedCourse() {
        professorInterface.viewSelectedCourses();
    }

    /**
     * method for handling student grading
     */
    private void handleStudentGrading() {
        System.out.println("Enter student Id: ");
        int studentId = sc.nextInt();

        System.out.println("Enter courseId: ");
        int courseId = sc.nextInt();

        System.out.println("Enter Grade: ");
        double grade = sc.nextDouble();

        professorInterface.addGrade(studentId, courseId, grade);
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
        UserOperation.logout();
    }

}
