package com.flipkart.application;

import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorInterfaceImpl;
import com.flipkart.business.UserInterfaceImpl;

import java.util.Scanner;

public class ProfessorClient {
    static Scanner sc = new Scanner(System.in);
    ProfessorInterface professorInterface = new ProfessorInterfaceImpl();
    static String professorId = null;

    public void showMenu(String emailId) {
        professorId = professorInterface.getProfessorIdByEmail(emailId);
        while (true) {
            showMenuItems();
            int userInput = sc.nextInt();

            if (userInput == 7) {
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
                    inputForUploadGrade();
                    break;
                case 6 :
                    viewAvailableCoursesToSelect();
                    break;
                default:
                    System.out.println("Invalid User Input");
            }
        }
    }

    private void viewAvailableCoursesToSelect() {
        professorInterface.getCourses("-1");
    }

    private void inputForUploadGrade() {
        System.out.println("Enter StudentId to whom you assign grade :");
        String studentId = sc.next();
        System.out.println("Enter CourseId :");
        String courseId = sc.next();
        System.out.println("Enter grade :");
        String grade = sc.next();
        professorInterface.addGrad(studentId, courseId, grade);
    }

    private void inputForViewEnrolledStudents() {
        professorInterface.viewEnrolledStudents(professorId);
    }

    private void inputsForViewSelectedCourses() {
        professorInterface.getCourses(professorId);
    }

    private void inputsForDeSelectCourse() {
        System.out.println("Input courseId you like to Deselect : ");
        String courseId = sc.next();
        if (!professorInterface.deselectCourse(professorId, courseId)) {
            System.out.println("You didn't select this course to unselect");
        } else {
            System.out.println("Successfully DeSelected the course");
        }
    }

    private void inputsForSelectCourse() {
        System.out.println("Input courseId you like to select : ");
        String courseId = sc.next();
        if (!professorInterface.selectCourse(professorId, courseId)) {
            System.out.println("Unable to select because already selected by others");
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
        System.out.println("6. View Available Courses to Select");
        System.out.println("7. Logout");
    }
}
