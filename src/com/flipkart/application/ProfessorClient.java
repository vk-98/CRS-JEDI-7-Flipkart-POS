package com.flipkart.application;

import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorInterfaceImpl;
import com.flipkart.business.UserInterfaceImpl;

import java.util.Scanner;

public class ProfessorClient {
    static Scanner sc = new Scanner(System.in);
    ProfessorInterface professorInterface = new ProfessorInterfaceImpl();
    static String professorId = null;

    /**
     * This method displays the menu items available for Professor
     * @param emailId - Using this we retrieve corresponding ProfessorId which is used across different operations
     */
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
                    viewSelectedCourses();
                    break;
                case 4:
                    viewEnrolledStudents();
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

    /**
     * Shows all the courses which are available to be selected
     */
    private void viewAvailableCoursesToSelect() {
        professorInterface.getCourses("-1");
    }

    /**
     * For Uploading grade to a student for a particular course, we need different inputs
     * The inputs are studentId, courseId, gpa
     */
    private void inputForUploadGrade() {
        System.out.println("Enter StudentId to whom you assign grade :");
        String studentId = sc.next();
        System.out.println("Enter CourseId :");
        String courseId = sc.next();
        System.out.println("Enter grade :");
        String grade = sc.next();
        professorInterface.addGrad(studentId, courseId, grade);
    }

    /**
     * It displays The students and their details who are all enrolled in to the courses that are being taught by the professor
     */
    private void viewEnrolledStudents() {
        professorInterface.viewEnrolledStudents(professorId);
    }

    /**
     * It displays which Available Courses are being selected by the professor
     */
    private void viewSelectedCourses() {
        professorInterface.getCourses(professorId);
    }

    /**
     * To Deselect a course, Professor provides input courseId
     */
    private void inputsForDeSelectCourse() {
        System.out.println("Input courseId you like to Deselect : ");
        String courseId = sc.next();
        if (!professorInterface.deselectCourse(professorId, courseId)) {
            System.out.println("You didn't select this course to unselect");
        } else {
            System.out.println("Successfully DeSelected the course");
        }
    }

    /**
     * To select a course, Professor provides input courseId
     */
    private void inputsForSelectCourse() {
        System.out.println("Input courseId you like to select : ");
        String courseId = sc.next();
        if (!professorInterface.selectCourse(professorId, courseId)) {
            System.out.println("Unable to select because already selected by others");
        } else {
            System.out.println("Successfully Selected the course");
        }
    }

    /**
     * Shows the Menu Items where Professor can select any option to perform corresponding operation
     */
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
