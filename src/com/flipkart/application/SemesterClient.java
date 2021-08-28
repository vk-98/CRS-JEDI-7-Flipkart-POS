package com.flipkart.application;

import com.flipkart.bean.OptedCourse;
import com.flipkart.business.SemesterRegistrationInterface;
import com.flipkart.business.SemesterRegistrationOperation;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author JEDI-07
 * Semester Client
 */
public class SemesterClient {

    private static Logger logger = Logger.getLogger(SemesterClient.class);
    Scanner sc = new Scanner(System.in);

    SemesterRegistrationInterface semesterRegistrationInterface = new SemesterRegistrationOperation();


    /**
     * method for semester menus
     */
    public void showSemesterMenu() {
        boolean exit = false;

        while (!exit) {
            showSemesterMenuItems();

            int userChoice = sc.nextInt();
            sc.nextLine();

            switch (userChoice) {
                case 1:
                    handleAddCourse(1);
                    break;
                case 2:
                    handleAddCourse(0);
                    break;
                case 3:
                    handleDropCourse();
                    break;
                case 4:
                    handleShowSelectedCourses();
                    break;
                case 5:
                    handleShowRegisteredCourses();
                    break;
                case 6:
                    handleSelectedCourseSubmission();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    logger.info("Invalid User Input");
            }

        }

    }

    /**
     * method for adding course
     *
     * @param isPrimary Indicates if the course is primary or not
     */
    private void handleAddCourse(int isPrimary) {
        boolean isCourseAdded;
        if (isPrimary == 1) {
            System.out.print("Enter primary courseID: ");
            int courseId = sc.nextInt();
            isCourseAdded = semesterRegistrationInterface.addCourse(courseId, 1);
        } else {
            System.out.println("Enter secondary courseID: ");
            int courseId = sc.nextInt();
            isCourseAdded = semesterRegistrationInterface.addCourse(courseId, 0);
        }
        if (isCourseAdded) {
            logger.info("Successfully added course");
        }
    }

    /**
     * method for dropping course
     */
    private void handleDropCourse() {
        System.out.print("Enter courseID to be dropped: ");
        int courseId = sc.nextInt();
        boolean isCourseDropped = semesterRegistrationInterface.dropCourse(courseId);
        if (isCourseDropped) {
            logger.info("Course Dropped Successfully");
        }
    }

    /**
     * method for displaying semester menu items
     */
    private void showSemesterMenuItems() {
        System.out.println("`####### Registration Menu #########`");
        System.out.println("1. Add Primary Course");
        System.out.println("2. Add Secondary Course");
        System.out.println("3. Drop Course");
        System.out.println("4. View Selected Courses");
        System.out.println("5. View Registered Courses");
        System.out.println("6. Submit your choices");
        System.out.println("7. Go back/cancel");
        System.out.print("Enter Your Choice : ");
    }

    /**
     * method for displaying selected courses by the student.
     */
    private void handleShowSelectedCourses() {
        List<OptedCourse> courses = semesterRegistrationInterface.getSelectedCourses();

        if (courses == null || courses.size() == 0) {
            logger.info("### No registered courses to show");
            return;
        }
        System.out.println("** Registered courses **");

        Formatter fmt = new Formatter();
        fmt.format("%20s %20s\n", "CourseId", "IsPrimary");
        for (OptedCourse course : courses) {
            fmt.format("%20s %20s\n", course.getCourseId(), course.isPrimary());
        }
        System.out.println(fmt);
    }

    /**
     * method for displaying registered courses for the semester.
     */
    private void handleShowRegisteredCourses() {
        List<OptedCourse> courses = semesterRegistrationInterface.getRegisteredCourses();

        if (courses == null || courses.size() == 0) {
            logger.info("### No registered courses to show");
            return;
        }
        System.out.println("** Registered courses **");

        Formatter fmt = new Formatter();
        fmt.format("%20s %20s\n", "CourseId", "IsPrimary");
        for (OptedCourse course : courses) {
            fmt.format("%20s %20s\n", course.getCourseId(), course.isPrimary());
        }
    }

    /**
     * method for submitting selected course
     */
    private void handleSelectedCourseSubmission() {
        boolean isSuccess = semesterRegistrationInterface.submitCourseChoices();
        if (isSuccess)
            logger.info("Selected Courses submitted successfully");
    }
}
