package com.flipkart.business;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Course;
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
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class SemesterRegistrationInterfaceImpl implements SemesterRegistrationInterface {

    SemesterRegistrationDaoInterface semesterRegistrationDaoInterface = new SemesterRegistrationDaoOperation();
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    private static Logger logger = Logger.getLogger(SemesterRegistrationInterfaceImpl.class);


    /**
     * Method to add a primary course for a given student
     * @param studentId
     * @param courseId
     * @return boolean indicating if the primary course is added successfully
     * @throws SQLException
     */
    @Override
    public boolean addPrimaryCourse(int studentId, int courseId) throws SQLException {

        if(semesterRegistrationDaoInterface.isRegistered(courseId,studentId))
        {
            logger.error("Course already registered");
            return false;
        }
        if (semesterRegistrationDaoInterface.addCourse(courseId, studentId, 0)) {
            logger.info("Successfully added primary course");
            return true;
        }


        logger.warn("Limit reached");

        return false;
    }


    /**
     * Method to add a secondary course for a given student
     * @param studentId
     * @param courseId
     * @return boolean indicating if the secondary course is added successfully
     * @throws SQLException
     */
    @Override
    public boolean addSecondaryCourse(int studentId, int courseId) throws SQLException {

        if(semesterRegistrationDaoInterface.isRegistered(courseId,studentId))
        {
            logger.error("Course already registered");
            return false;
        }
        if (semesterRegistrationDaoInterface.addCourse(courseId, studentId, 1)) {
            logger.info("Successfully added secondary course");
            return true;
        }


        logger.error("Limit reached");

        return false;
    }

    /**
     * Method to drop a course for a given student
     * @param studentId
     * @param courseId
     * @return boolean indicating if the course is dropped successfully
     * @throws SQLException
     */
    @Override
    public boolean dropCourse(int studentId, int courseId) {

        if (semesterRegistrationDaoInterface.dropCourse(courseId, studentId)) {
            logger.info("Course Deleted");
            return true;
        }

        logger.error("Not found");
        return false;

    }


    /**
     * Method to view all the courses registered by the given student
     * @param studentId
     * @return
     */
    @Override
    public void viewRegisteredCourses(int studentId) {

        List<OptedCourse> courses = semesterRegistrationDaoInterface.viewRegisteredCourses(studentId);

        if (courses.size() == 0) {
            logger.info("### No registered courses to show");
            return;
        }
        System.out.println("** Registered courses **");

        Formatter fmt = new Formatter();
        fmt.format("%20s %20s\n", "CourseId", "IsPrimary");
        for (OptedCourse course: courses) {
            fmt.format("%20s %20s\n", course.getCourseId(), course.isPrimary());
        }
        System.out.println(fmt);
    }


    /**
     * Method to calculate fee for a given student
     * @param studentId
     * @return total fee to be paid by a given student
     * @throws SQLException
     */
    @Override
    public double calculateFee(int studentId) throws SQLException {
        double totalFee = 0;
        totalFee = semesterRegistrationDaoInterface.calculateFee(studentId);
        return totalFee;
    }


    /**
     * Method to submit the choices of the course opted by the student
     * @param studentId
     * @return boolean indicating if the choices are added successfully
     * @throws NoRegisteredCourseException
     * @throws CourseCountException
     * @throws SeatNotAvailableException
     * @throws SQLException
     */
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
                    logger.info("Courses submitted successfully");
                    if (semesterRegistrationDaoInterface.setRegistrationStatus(studentId, 1))
                        logger.info("Semester is Registered successfully");
                    ;
                    return true;
                }
                logger.error("Invalid selection");
            }
        } catch (NoRegisteredCourseException ex) {
            logger.info(NoRegisteredCourseException.msg);
        } catch (CourseCountException ex) {
            logger.info("Your " + ex.getCourseType() + " course count is " + ex.getCourseCount() + " but it should be " + ex.getRequiredCourseCount());
        } catch (SeatNotAvailableException ex) {
            logger.info("Seats are not available in : " + ex.getCourseId() + " course");
        } catch (CourseLimitExceededException e) {
            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
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


    /**
     * Method to set payment status of the given student
     * @param studentId
     * @param status
     * @return boolean indicating if the payment status is changes successfully
     * @throws SQLException
     */
    @Override
    public void setPaymentStatus(int studentId, boolean status) throws SQLException {
        if (semesterRegistrationDaoInterface.setPaymentStatus(studentId, status ? 1 : 0)) {
            logger.info("## Payment is Successful");
            Notification notification = new Notification(true, "Welcome to the semester student ", studentId);
            if (notificationDaoInterface.sendNotification(notification))
                logger.info("Notification Sent");
            return;
        }
        logger.error("Payment Failed");
    }
}
