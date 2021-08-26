package com.flipkart.business;

import com.flipkart.bean.Notification;
import com.flipkart.bean.OptedCourse;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.dao.SemesterRegistrationDaoInterface;
import com.flipkart.dao.SemesterRegistrationDaoOperation;
import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.CourseLimitExceededException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SemesterRegistrationImpl implements SemesterRegistrationInterface {


    SemesterRegistrationDaoInterface semesterRegistrationDaoInterface = new SemesterRegistrationDaoOperation();
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    @Override
    public boolean addPrimaryCourse(int studentId, int courseId) {

        if (semesterRegistrationDaoInterface.addCourse(courseId, studentId, 0)) {
            System.out.println("Successfully added primary course");
            return true;
        }


        System.out.println("Limit reached");

        return false;
    }

    @Override
    public boolean addSecondaryCourse(int studentId, int courseId) {
        if (semesterRegistrationDaoInterface.addCourse(courseId, studentId, 1)) {
            System.out.println("Successfully added secondary course");
            return true;
        }


        System.out.println("Limit reached");

        return false;
    }

    @Override
    public boolean dropCourse(int studentId, int courseId) {

        if (semesterRegistrationDaoInterface.dropCourse(courseId, studentId)) {
            System.out.println("Course Deleted");
            return true;
        }

        System.out.println("Not found");
        return false;

    }


    @Override
    public void viewRegisteredCourses(int studentId) {

        List<OptedCourse> courses = semesterRegistrationDaoInterface.viewRegisteredCourses(studentId);

        if (courses.size() == 0) {
            System.out.println("### No registered courses to show");
            return;
        }
        System.out.println("** Registered courses **");
        System.out.println("CourseId | isPrimary");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(
                    courses.get(i).getCourseId() +
                            " | " +
                            courses.get(i).isPrimary()

            );
        }

    }

    @Override
    public double calculateFee(int studentId) throws SQLException {
        double totalFee = 0;
        totalFee = semesterRegistrationDaoInterface.calculateFee(studentId);
        return totalFee;
    }

    @Override
    public boolean submitCourseChoices(int studentId) throws NoRegisteredCourseException, CourseCountException, SeatNotAvailableException, SQLException {
        viewRegisteredCourses(studentId);
        System.out.println("Do you want to proceed?  ");
        System.out.println("Enter 1 for yes");
        System.out.println("Enter 2 for no");

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        try {
            if (x == 1) {
                List<Integer> ls = semesterRegistrationDaoInterface.getPrimarySecondaryCoursesCount(studentId);
                if (ls.size() == 0) {
                    throw new NoRegisteredCourseException();
                }
                if (ls.get(0) != 4) {
                    throw new CourseCountException("primary", ls.get(0), 4);
                }
                if (ls.get(1) != 2) {
                    throw new CourseCountException("secondary", ls.get(1), 2);
                }
                if (ls.get(0) + ls.get(1) != 6)
                    throw new CourseLimitExceededException(ls.get(0) + ls.get(1));

                int cId = semesterRegistrationDaoInterface.getCourseIdIfSeatNotAvailable(studentId);

                if (cId != -1) {
                    throw new SeatNotAvailableException(cId);
                }

                if (semesterRegistrationDaoInterface.submitChoices(studentId)) {
                    System.out.println("Courses submitted successfully");
                    if (semesterRegistrationDaoInterface.setRegistrationStatus(studentId, 1))
                        System.out.println("Semester is Registered successfully");
                    ;
                    return true;
                }
                System.out.println("Invalid selection");
            }
        } catch (NoRegisteredCourseException ex) {
            System.out.println(NoRegisteredCourseException.msg);
        } catch (CourseCountException ex) {
            System.out.println("Your " + ex.getCourseType() + " course count is " + ex.getCourseCount() + " but it should be " + ex.getRequiredCourseCount());
        } catch (SeatNotAvailableException ex) {
            System.out.println("Seats are not available in : " + ex.getCourseId() + " course");
        } catch (CourseLimitExceededException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean getRegistrationStatus(int studentId) throws SQLException {
        return semesterRegistrationDaoInterface.getRegistrationStatus(studentId);

    }

    @Override
    public boolean getPaymentStatus(int studentId) throws SQLException {
        return semesterRegistrationDaoInterface.getPaymentStatus(studentId);

    }

    @Override
    public void setRegistrationStatus(int studentId, boolean status) {

    }

    @Override
    public void setPaymentStatus(int studentId, boolean status) throws SQLException {
        if (semesterRegistrationDaoInterface.setPaymentStatus(studentId, status ? 1 : 0)) {
            System.out.println("## Payment is Successful");
            Notification notification = new Notification(true, "Welcome to the semester student ", studentId);
            if (notificationDaoInterface.sendNotification(notification))
                System.out.println("Notification Sent");
            return;
        }
        System.out.println("Payment Failed");
    }
}
