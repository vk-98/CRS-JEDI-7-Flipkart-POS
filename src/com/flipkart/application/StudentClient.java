package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.business.*;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author JEDI-07
 * Student Client
 */
public class StudentClient {

    private static Logger logger = Logger.getLogger(StudentClient.class);
    Scanner sc = new Scanner(System.in);
    CourseInterface courseInterface = new CourseOperation();
    SemesterRegistrationInterface semesterRegistrationInterface = new SemesterRegistrationOperation();
    UserInterface userInterface = new UserOperation();
    StudentInterface studentInterface = new StudentOperation();
    NotificationInterface notificationInterface = new NotificationOperation();
    SemesterClient semesterClient = new SemesterClient();


    /**
     * method for student menus
     */
    public void showMenu() {

        studentInterface.getStudentByEmailId();

        if (!StudentOperation.student.isApproved()) {
            logger.info("Your admission request is still pending..., login later");
            return;
        }
        logger.info("User Logged in Successfully");
        boolean exit = false;

        while (!exit) {
            showStudentMenu();

            int userInput = sc.nextInt();
            sc.nextLine();

            switch (userInput) {
                case 1:
                    handleSemesterRegistration();
                    break;
                case 2:
                    handleViewCourses();
                    break;
                case 3:
                    handleFeePayment();
                    break;
                case 4:
                    handleShowNotification();
                    break;
                case 5:
                    handleViewGrades();
                    break;
                case 6:
                    handleUpdatePassword();
                    break;
                case 7:
                    handleLogout();
                    exit = true;
                    break;
                default:
                    logger.warn("Invalid User Input");
            }
        }
    }

    /**
     * method for showing student action menu
     */
    private void showStudentMenu() {
        System.out.println("*********************************************************************************");
        System.out.println("******************************** Student Menu ***********************************");
        System.out.println("*********************************************************************************");
        System.out.println("1. Semester Registration");
        System.out.println("2. View Courses");
        System.out.println("3. Pay Fees");
        System.out.println("4. Show Notifications");
        System.out.println("5. View Grades");
        System.out.println("6. Update Password");
        System.out.println("7. Logout");
        System.out.print("Enter User Input :");
    }

    /**
     * method for semester registration.
     */
    private void handleSemesterRegistration() {
        semesterClient.showSemesterMenu();
    }

    /**
     * method for view courses.
     */
    private void handleViewCourses() {
        List<Course> courses = courseInterface.getCourses();
        if (courses == null || courses.size() == 0) {
            logger.info("No Available Courses");
        } else {
            Formatter fmt = new Formatter();
            fmt.format(
                    "%30s  %30s  %30s  %30s  %30s  %30s\n", "CourseID", "CourseName", "CourseDescription", "ProfessorID", "CourseFee", "StudentCount"
            );
            for (Course c : courses) {
                fmt.format(
                        "%30s  %30s  %30s  %30s  %30s  %30s\n",
                        c.getCourseId(),
                        c.getCourseName(),
                        c.getCourseDescription(),
                        c.getProfessorId(),
                        c.getCourseFee(),
                        c.getStudentCount()
                );

            }
            System.out.println(fmt);
        }
    }

    /**
     * method for fee payment of loggedin student
     */
    public void handleFeePayment() {
        double feePending = semesterRegistrationInterface.getPendingFee();
        if (feePending != 0) {
            System.out.println("Paying Fee: " + feePending);
            boolean feePayed = semesterRegistrationInterface.payFee(feePending);
            if (feePayed) {
                logger.info("Fee payement completed.");
            } else {
                logger.error("Fee payment failed");
            }
        } else {
            logger.info("No Pending fees.");
        }
    }

    /**
     * method to show student grades of regsitered courses.
     */
    private void handleViewGrades() {
        List<Grade> grades = studentInterface.getGrades();
        if (grades == null || grades.size() == 0) {
            logger.info("No grades available at the moment.");
        } else {
            System.out.println("********************* GRADE CARD *********************");
            Formatter fmt = new Formatter();
            fmt.format("%10s %20s %10s\n", "CourseId", "CourseName", "GPA");
            double totalGrades = 0;
            for (Grade grade : grades) {
                fmt.format("%10s %20s %10s\n", grade.getCourseId(), grade.getCourseName(), grade.getGpa());
                totalGrades += grade.getGpa();
            }
            System.out.println(fmt);
            double cgpa = totalGrades / grades.size();
            System.out.println("Your CGPA is " + cgpa);
        }
    }

    /**
     * method for displaying all notifications
     */
    private void handleShowNotification() {
        List<Notification> notifications = notificationInterface.getNotifications();
        if (notifications == null || notifications.size() == 0) {
            logger.info("No Notification at the moment.");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%20s %30s\n", "NotificationId", "NotificationContent");
            for (Notification notification : notifications) {
                fmt.format("%20s %30s\n", notification.getNotificationId(), notification.getContent());
            }
            System.out.println(fmt);
        }
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
        boolean userLoggedOut = userInterface.logout();
        if(userLoggedOut) {
            logger.info("You are successfully logged out.");
        }
    }
}