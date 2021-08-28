package com.flipkart.application;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.*;
import org.apache.log4j.Logger;

/**
 * @author JEDI-07
 * Admin Client
 */
public class AdminClient {
    private static Logger logger = Logger.getLogger(AdminClient.class);
    Scanner sc = new Scanner(System.in);
    AdminInterface adminInterface = new AdminOperation();
    CourseInterface courseInterface = new CourseOperation();
    UserInterface userInterface = new UserOperation();

    /**
     * method for admin menu
     */
    public void showMenu() {
        logger.info("User Logged in Successfully");

        boolean exit = false;

        while (!exit) {
            showAdminMenu();

            int userInput = sc.nextInt();
            sc.nextLine();

            switch (userInput) {
                case 1:
                    handleViewCourses();
                    break;
                case 2:
                    handleAddCourse();
                    break;
                case 3:
                    handleRemoveCourse();
                    break;
                case 4:
                    handleAddProfessor();
                    break;
                case 5:
                    handleViewProfessor();
                    break;
                case 6:
                    handelViewAdmissionRequests();
                    break;
                case 7:
                    handleApproveStudentAdmissionRequest();
                    break;
                case 8:
                    handleUpdatePassword();
                    break;
                case 9:
                    handleLogout();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid User Input");
            }

        }
    }

    /**
     * Method for displaying admin action menu
     */
    private void showAdminMenu() {
        System.out.println("*********************************************************************************");
        System.out.println("********************************* Admin Menu ************************************");
        System.out.println("*********************************************************************************");
        System.out.println("1. View Courses");
        System.out.println("2. Add Course");
        System.out.println("3. Remove Course");
        System.out.println("4. Add Professor");
        System.out.println("5. List Professors");
        System.out.println("6. List Admission Requests");
        System.out.println("7. Approve Student");
        System.out.println("8. Update Password");
        System.out.println("9. Logout");
        System.out.print("Enter User Input: ");
    }

    /**
     * method for view courses
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
     * method for adding course
     */
    private void handleAddCourse() {

        System.out.println("Enter details of the course to be added: ");

        System.out.print("Course Name: ");
        String courseName = sc.nextLine();

        System.out.print("Course Description: ");
        String description = sc.nextLine();

        System.out.print("Course Fee: ");
        double courseFee = sc.nextDouble();

        boolean isCourseAdded = adminInterface.addCourse(courseName, description, courseFee);
        if (isCourseAdded)
            logger.info("Course Added Successfully");
    }

    /**
     * method for removing course
     */
    private void handleRemoveCourse() {

        System.out.print("Enter ID of the course to be deleted: ");
        int courseId = sc.nextInt();

        boolean isCourseRemoved = adminInterface.removeCourse(courseId);

        if (isCourseRemoved)
            logger.info("Course removed successfully");
    }

    /**
     * method for adding professor
     */
    private void handleAddProfessor() {
        System.out.println("Enter details of the Professor to be added: ");

        System.out.print("Enter Name - ");
        String professorName = sc.nextLine();

        System.out.print("Enter Email Id - ");
        String emailId = sc.nextLine();

        System.out.print("Enter Password - ");
        String password = sc.nextLine();

        System.out.print("Enter Phone No - ");
        String phoneNo = sc.nextLine();

        System.out.print("Enter Department - ");
        String department = sc.nextLine();

        System.out.print("Enter Designation - ");
        String designation = sc.nextLine();

        boolean isProfessorAdded = adminInterface.addProfessor(
                professorName,
                emailId,
                password,
                phoneNo,
                department,
                designation
        );

        if (isProfessorAdded) {
            logger.info("Professor created successfully.");
        }
    }

    /**
     * method for viewing all available course
     */
    private void handleViewProfessor() {
        List<Professor> professors = adminInterface.getProfessors();
        if (professors == null || professors.size() == 0) {
            logger.info("No Professors registered");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%30s %30s %30s %30s %30s\n", "ProfId", "ProfName", "ProfEmail ID", "ProfDepartment", "ProfDesignation");
            for (Professor p : professors) {
                fmt.format("%30s %30s %30s %30s %30s\n", p.getProfessorId(), p.getUserName(), p.getUserEmailId(), p.getDepartment(), p.getDesignation());
            }
            System.out.println(fmt);
        }
    }

    /**
     * method for viewing all available admission requests.
     */
    private void handelViewAdmissionRequests() {
        List<Student> admissionRequests = adminInterface.getAdmissionRequests();
        if (admissionRequests == null || admissionRequests.size() == 0) {
            logger.info("No pending addmission requests");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%30s %30s %30s\n", "StudentID", "StudentName", "StudentEmailId");
            for (Student st : admissionRequests) {
                fmt.format("%30s %30s %30s\n", st.getStudentId(), st.getUserName(), st.getUserEmailId());
            }
            System.out.println(fmt);
        }
    }

    /**
     * method for updating password.
     */
    private void handleUpdatePassword() {
        System.out.print("Enter New Password: ");
        String newPassword = sc.nextLine();
        boolean isPasswordUpdated = userInterface.updateUserPassword(newPassword);
        if(isPasswordUpdated)
            logger.info("User Password Updated Successfully");
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

    /**
     * method for approving student admission request.
     */
    private void handleApproveStudentAdmissionRequest() {
        System.out.print("Enter Student Id - ");
        int studentId = sc.nextInt();
        boolean isApproved = adminInterface.approveStudentRequest(studentId);
        if (isApproved) {
            logger.info("Student Addmission Request approved");
        }
    }
}