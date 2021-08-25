package com.flipkart.application;

import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorInterfaceImpl;
import com.flipkart.business.UserInterfaceImpl;

import java.util.Scanner;

public class ProfessorClient {
    static Scanner sc = new Scanner(System.in);
    ProfessorInterface professorInterface = new ProfessorInterfaceImpl();

    public void showMenu() {

        while (true) {
            showMenuItems();
            int userInput = sc.nextInt();

            if (userInput == 6) {
                UserInterfaceImpl.logout();
                break;
            }

            switch (userInput) {
                case 1:
                    inputsForSelectCourse();
                    break;
                case 2:
                    inputsForDeSelectCourse();
                    break;
                case 3:
                    inputsForViewSelectedCourses();
                    break;
                case 4:
                    inputForViewEnrolledStudents();
                    break;
                case 5:
                    System.out.println("Upload Grade");
                    break;
                default:
                    System.out.println("Invalid User Input");
            }
        }
    }

    private void inputForViewEnrolledStudents() {
        System.out.println("Professor, Input your Id ");
        String professorId = sc.next();
        professorInterface.viewEnrolledStudents(professorId);
    }

    private void inputsForViewSelectedCourses() {
        System.out.println("Professor, Input your Id ");
        String professorId = sc.next();
        professorInterface.getCourses(professorId);
    }

    private void inputsForDeSelectCourse() {
        System.out.println("Input courseId you like to Deselect : ");
        String courseId = sc.next();
        if (!professorInterface.deselectCourse(courseId)) {
            System.out.println("CourseId is Invalid");
        } else {
            System.out.println("Successfully Selected the course");
        }
    }

    private void inputsForSelectCourse() {
        System.out.println("Professor, Enter your Id : ");
        String professorId = sc.next();
        System.out.println("Input courseId you like to select : ");
        String courseId = sc.next();
        if (!professorInterface.selectCourse(professorId, courseId)) {
            System.out.println("CourseId is Invalid");
        } else {
            System.out.println("Successfully Selected the course");
        }
    }

    private void showMenuItems() {
        System.out.println("1. Select Course");
        System.out.println("2. Deselect Course");
        System.out.println("3. View Selected Course");
        System.out.println("4. View Enrolled Students");
        System.out.println("5. Upload Grade");
        System.out.println("6. Logout");
    }
}
