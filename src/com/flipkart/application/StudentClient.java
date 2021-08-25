package com.flipkart.application;

import com.flipkart.business.*;

import java.util.Scanner;


public class StudentClient {

    Scanner sc = new Scanner(System.in);
    CourseInterface courseInterface = new CourseInterfaceImpl(10);
    SemesterRegistrationInterface semRegister = new SemesterRegistrationImpl();
    UserInterface userInterface = new UserInterfaceImpl();

    public void showMenu(String studentId) {
        boolean menuBreakFlag = false;

        while (!menuBreakFlag) {

            showStudentMenu();
            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    semesterRegistration(studentId);
                    break;
                case 2:
                    System.out.println("View Courses");
                    courseInterface.viewCourses();
                    break;
                case 3:
                    feePayment(studentId);
                    break;
                case 4:
                    String newPassword = sc.next();
                    userInterface.updateUserPassword(newPassword);
                    break;
                case 5:
                    menuBreakFlag = true;
                    UserInterfaceImpl.logout();
                    break;
                default:
                    System.out.println("Invalid User Input");
            }
        }
    }

    static void showStudentMenu() {
        System.out.println("*********************************************************************************");
        System.out.println("********************************* Student Menu **********************************");
        System.out.println("*********************************************************************************");
        System.out.println("1. Semester Registration");
        System.out.println("2. View Courses");
        System.out.println("3. Pay Fees");
        System.out.println("4. Update Password");
        System.out.println("5. Logout");
        System.out.print("Enter User Input :");
    }

    public void semesterRegistration(String studentId) {
        boolean breakFlag = false;


        while (!breakFlag) {

            System.out.println("`##### Registration Menu #######`");
            System.out.println("1. Add Primary Course");
            System.out.println("2. Add Secondary Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Submit your choices");
            System.out.println("6. Go back/cancel");
            System.out.println("Enter Your Choice :");


            int userChoice = sc.nextInt();


            switch (userChoice) {
                case 1: {
                    System.out.println("Enter primary courseID:");
                    String courseId = sc.next();
                    semRegister.addPrimaryCourse(studentId, courseId);
                    break;
                }

                case 2: {
                    System.out.println("Enter secondary courseID:");
                    String courseId = sc.next();
                    semRegister.addSecondaryCourse(studentId, courseId);
                    break;
                }

                case 3: {
                    System.out.println("Enter courseID to be dropped:");
                    String courseId = sc.next();
                    semRegister.dropCourse(studentId, courseId);
                    break;
                }

                case 4:
                    semRegister.viewRegisteredCourses(studentId);
                    break;

                case 5: {
                    boolean isSuccess = semRegister.submitCourseChoices(studentId);
                    if (isSuccess)
                        breakFlag = true;
                    break;
                }

                case 6:
                    breakFlag = true;
                    break;

                default:
                    System.out.println("Invalid Choice !!");

            }

        }

    }

    public void viewCourses() {
        // show all the courses
    }

    public void feePayment(String studentId) {

        double totalFee = semRegister.calculateFee(studentId);
        //System.out.println("Please proceed with the payment of this amount : " + totalFee);

        System.out.println("## Total Fees : " + totalFee);

        // get the corresponding student object
        // invoke  boolean payFee(String studentId, String studentRegistrationId, double amount)
    }

}
