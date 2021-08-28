package com.flipkart.business;

import com.flipkart.bean.OptedCourse;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.dao.SemesterRegistrationDaoInterface;
import com.flipkart.dao.SemesterRegistrationDaoOperation;
import com.flipkart.exceptions.CourseCountException;
import com.flipkart.exceptions.NoRegisteredCourseException;
import com.flipkart.exceptions.SeatNotAvailableException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JEDI-07
 * Implementation of Semester Registration Interface
 */
public class SemesterRegistrationOperation implements SemesterRegistrationInterface {

    SemesterRegistrationDaoInterface semesterRegistrationDaoInterface = new SemesterRegistrationDaoOperation();
    NotificationOperation notificationOperation = new NotificationOperation();

    private static Logger logger = Logger.getLogger(SemesterRegistrationOperation.class);


    /**
     * method for adding course for the logged in user
     *
     * @param courseId  unique Id to represent a course
     * @param isPrimary isPrimary Indicates if the course is primary or not
     * @return returns true if the course is added successfully
     */
    @Override
    public boolean addCourse(int courseId, int isPrimary) {
        // Exception
        boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();

        if (isRegistered) {
            logger.info("Student already registered for the semester");
            return false;
        }

        if (isPrimary == 1) {
            int primaryCourseCount = semesterRegistrationDaoInterface.getCourseCount(1);
            if (primaryCourseCount >= 4) {
                logger.info("4 Primary Courses already selected.");
                return false;
            }
        } else {
            int secondaryCourseCount = semesterRegistrationDaoInterface.getCourseCount(0);
            if (secondaryCourseCount >= 2) {
                logger.info("2 secondary Courses already selected.");
                return false;
            }
        }
        boolean isCourseAlreadyRegistered = semesterRegistrationDaoInterface.isCourseAlreadyRegistered(courseId);

        if (isCourseAlreadyRegistered) {
            logger.info("Course already registered");
            return false;
        }

        boolean isAvailable = semesterRegistrationDaoInterface.checkAvailability(courseId);

        if (!isAvailable) {
            logger.info("Maximum limit of students for courseId " + courseId + " reached.");
            return false;
        }

        int semesterId = semesterRegistrationDaoInterface.getSemesterId();

        if (semesterId == 0) {
            boolean registered = semesterRegistrationDaoInterface.registerForSemester();
            if (registered) {
                semesterId = semesterRegistrationDaoInterface.getSemesterId();
            }
        }

        return semesterRegistrationDaoInterface.addCourse(courseId, semesterId, isPrimary);
    }


    /**
     * method for dropping course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is dropped successfully
     */
    @Override
    public boolean dropCourse(int courseId) {
        // Exception
        boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();

        if (isRegistered) {
            logger.info("Student already registered for the semester");
            return false;
        }
        boolean isCourseRegistered = semesterRegistrationDaoInterface.isCourseAlreadyRegistered(courseId);

        if (isCourseRegistered) {
            return semesterRegistrationDaoInterface.dropCourse(courseId);
        } else {
            logger.info("Course is not registered");
        }
        return false;
    }


    /**
     * method for getting registered courses
     *
     * @return list of registered courses
     */
    @Override
    public List<OptedCourse> getRegisteredCourses() {
        return semesterRegistrationDaoInterface.getRegisteredCourses();
    }

    /**
     * method for getting selected courses
     *
     * @return list of selected courses
     */
    @Override
    public List<OptedCourse> getSelectedCourses() {
        return semesterRegistrationDaoInterface.getSelectedCourses();
    }


    /**
     * method for submitting course choices
     *
     * @return returns true if course choices are submitted successfully
     */
    @Override
    public boolean submitCourseChoices() {

        boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();

        if (isRegistered) {
            logger.info("Student already registered for the semester");
            return false;
        }

        List<OptedCourse> courses = semesterRegistrationDaoInterface.getSelectedCourses();
        List<OptedCourse> primaryCourse = courses.stream().filter(course -> course.isPrimary()).collect(Collectors.toList());
        List<OptedCourse> secondaryCourse = courses.stream().filter(course -> !course.isPrimary()).collect(Collectors.toList());

        if (courses == null || courses.size() == 0) {
            logger.info("No courses Selected for registeration.");
            return false;
        }

        logger.info(
                "You have selected "
                        + primaryCourse.size()
                        + " primary courses and "
                        + secondaryCourse.size()
                        + "secondary courses."
        );
        if (courses.size() < 6) {
            logger.info("Please Select 4 Primary and 2 Seconday Courses.");
            return false;
        }

        int courseCount = 0;
        double courseFee = 0;

        for (OptedCourse course : primaryCourse) {
            boolean isAvailable = semesterRegistrationDaoInterface.checkAvailability(course.getCourseId());
            if (isAvailable) {
                boolean alloted = semesterRegistrationDaoInterface.allotCourse(course.getCourseId());
                if (alloted) {
                    semesterRegistrationDaoInterface.updateStudentCount(course.getCourseId());
                    courseCount += 1;
                    courseFee += course.getCourseFee();
                }
            }
        }

        for (OptedCourse course : secondaryCourse) {
            if (courseCount < 4) {
                boolean isAvailable = semesterRegistrationDaoInterface.checkAvailability(course.getCourseId());
                if (isAvailable) {
                    boolean alloted = semesterRegistrationDaoInterface.allotCourse(course.getCourseId());
                    if (alloted) {
                        semesterRegistrationDaoInterface.updateStudentCount(course.getCourseId());
                        courseCount += 1;
                        courseFee += course.getCourseFee();

                    }
                }
            }
        }

        boolean submitRegistration = semesterRegistrationDaoInterface.submitRegistration(courseFee);

        String notificationContent = "You have Successfully Registered for the Semester. Please Pay fee $" + courseFee + " ASAP";
        notificationOperation.sendNotification(notificationContent);

        return submitRegistration;
    }

    /**
     * method for getting the pending fee.
     *
     * @return pending Fee
     */
    @Override
    public double getPendingFee() {
        return semesterRegistrationDaoInterface.getPendingFee();
    }

    /**
     * method for paying fee
     *
     * @param amount Amount to be paid
     * @return returns true if payment is successful
     */
    @Override
    public boolean payFee(double amount) {
        boolean feePayment = semesterRegistrationDaoInterface.payFee(amount);
        if (feePayment) {
            String notificationContent = "Fee Payment Complete Welcome to the CRS.";
            notificationOperation.sendNotification(notificationContent);
        }
        return feePayment;
    }
}
