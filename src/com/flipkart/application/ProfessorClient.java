package com.flipkart.application;

import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorInterfaceImpl;
import com.flipkart.business.UserInterfaceImpl;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ProfessorClient {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    static Scanner sc = new Scanner(System.in);
    ProfessorInterface professorInterface = new ProfessorInterfaceImpl();

    public void showMenu() {

        logger.info("User Logged in Successfully");
        professorInterface.getProfessor();
        boolean menuBreakFlag = false;
        while (!menuBreakFlag) {
            showMenuItems();
            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    professorInterface.viewAvailableCourses();
                    break;
                case 2:
                    inputsForSelectCourse();
                    break;
                case 3:
                    inputsForDeSelectCourse();
                    break;
                case 4:
                    professorInterface.viewSelectedCourses();
                    break;
                case 5:
                    inputForViewEnrolledStudents();
                    break;
                case 6:
                    inputForGradingStudent();
                    break;
                case 7:
                    UserInterfaceImpl.logout();
                    menuBreakFlag = true;
                    break;
                default:
                    System.out.println("Invalid User Input");
            }
        }
    }

    private void showMenuItems() {
        System.out.println("1. View Available Course");
        System.out.println("2. Select Course");
        System.out.println("3. Deselect Course");
        System.out.println("4. View Selected Course");
        System.out.println("5. View Enrolled Students");
        System.out.println("6. Add Grade");
        System.out.println("7. Logout");
        System.out.print("Enter User Input: ");
    }

    private void inputForViewEnrolledStudents() {
        System.out.print("Enter the courseId: ");
        int courseId = sc.nextInt();
        professorInterface.viewEnrolledStudents(courseId);
    }


    private void inputsForDeSelectCourse() {
        System.out.print("Input courseId you like to Deselect : ");
        int courseId = sc.nextInt();
        professorInterface.deselectCourse(courseId);
    }

    private void inputsForSelectCourse() {
        System.out.print("Input courseId you like to select : ");
        int courseId = sc.nextInt();
        professorInterface.selectCourse(courseId);
    }

    private void inputForGradingStudent() {
        System.out.println("Enter student Id: ");
        int studentId = sc.nextInt();

        System.out.println("Enter courseId: ");
        int courseId = sc.nextInt();

        System.out.println("Enter Grade: ");
        double grade = sc.nextDouble();

        professorInterface.addGrade(studentId, courseId, grade);
    }

}
