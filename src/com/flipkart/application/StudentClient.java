package com.flipkart.application;

import com.flipkart.bean.Student;
import com.flipkart.business.*;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;

import java.sql.SQLException;
import java.util.Scanner;


public class StudentClient {

    Scanner sc = new Scanner(System.in);
    CourseInterface courseInterface = new CourseInterfaceImpl();
    SemesterRegistrationInterface semRegister = new SemesterRegistrationImpl();
    UserInterface userInterface = new UserInterfaceImpl();
    StudentInterface studentInterface = new StudentInterfaceImpl();
    NotificationInterface notificationInterface= new NotificationImpl();

    public void showMenu() throws SQLException, CourseCountException, NoRegisteredCourseException, SeatNotAvailableException {

        boolean menuBreakFlag = false;

        studentInterface.setStudent();

        if(!StudentInterfaceImpl.student.isApproved()) {
            System.out.println("Your admission request is still pending..., login later");
            return;
        }


        System.out.println("User Logged in Successfully");

        while (!menuBreakFlag) {

            showStudentMenu();
            int userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    semesterRegistration(StudentInterfaceImpl.student.getStudentId());
                    break;
                case 2:
                    System.out.println("View Courses");
                    courseInterface.viewCourses();
                    break;
                case 3:
                    feePayment(StudentInterfaceImpl.student.getStudentId());
                    break;
                case 4:
                    System.out.print("Enter New Password: ");
                    String newPassword = sc.next();
                    userInterface.updateUserPassword(newPassword);
                    break;
                case 5:
                    notificationInterface.showNotifications(StudentInterfaceImpl.student.getStudentId());
                    break;
                case 6:
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
        System.out.println("5. Show Notifications");
        System.out.println("6. Logout");
        System.out.print("Enter User Input :");
    }

    public void semesterRegistration(int studentId) throws CourseCountException, NoRegisteredCourseException, SeatNotAvailableException, SQLException {
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
                    int courseId = sc.nextInt();
                    semRegister.addPrimaryCourse(studentId, courseId);
                    break;
                }

                case 2: {
                    System.out.println("Enter secondary courseID:");
                    int courseId = sc.nextInt();
                    semRegister.addSecondaryCourse(studentId, courseId);
                    break;
                }

                case 3: {
                    System.out.println("Enter courseID to be dropped:");
                    int courseId = sc.nextInt();
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

    public void feePayment(int studentId) throws SQLException {

        boolean status = semRegister.getRegistrationStatus(studentId);
        System.out.println(status);
        if(!status)
        {
            System.out.println("First do semester registration");
            return;
        }
        status= semRegister.getPaymentStatus(studentId);
        if(status)
        {
            System.out.println("Already paid");
            return;
        }
        double totalFee = semRegister.calculateFee(studentId);

        System.out.println("## Total Fees : " + totalFee);
        System.out.println("Do you want to proceed");
        System.out.println("Enter 1 to proceed");
        System.out.println("Enter 2 to cancel");

        int x= sc.nextInt();
        if(x==1)
        {
            System.out.println("Enter 1 for online payment");
            System.out.println("Enter 2 for cash/cheque payment");
            x=sc.nextInt();
            semRegister.setPaymentStatus(studentId,true);
        }
    }

}
