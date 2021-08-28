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
 * Admin Client
 */
public class SemesterRegistrationOperation implements SemesterRegistrationInterface {

    SemesterRegistrationDaoInterface semesterRegistrationDaoInterface = new SemesterRegistrationDaoOperation();
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    private static Logger logger = Logger.getLogger(SemesterRegistrationOperation.class);


    /**
     * Method to add a primary course for a given student
     *
     * @param courseId
     * @return boolean indicating if the primary course is added successfully
     * @throws SQLException
     */
    @Override
    public boolean addCourse(int courseId, int isPrimary)  {

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
        System.out.println(semesterId);
        boolean courseAdded = semesterRegistrationDaoInterface.addCourse(courseId, semesterId, isPrimary);
        System.out.println(courseAdded);
        if (courseAdded) {
            logger.info("Successfully added course");
            return true;
        }

        return false;
    }


    /**
     * Method to drop a course for a given student
     *
     * @param courseId
     * @return boolean indicating if the course is dropped successfully
     * @throws SQLException
     */
    @Override
    public boolean dropCourse(int courseId)  {
        boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();

        if (isRegistered) {
            logger.info("Student already registered for the semester");
            return false;
        }
        boolean isCourseRegistered = semesterRegistrationDaoInterface.isCourseAlreadyRegistered(courseId);

        if (isCourseRegistered) {
            boolean courseDropped = semesterRegistrationDaoInterface.dropCourse(courseId);
            if (courseDropped) {
                logger.info("Course Dropped Successfully");
                return true;
            }
        } else {
            logger.info("Course is not registered");
        }
        return false;
    }


    /**
     * Method to view all the courses registered by the given student
     *
     * @return
     */
    @Override
    public void getRegisteredCourses() {

        List<OptedCourse> courses = semesterRegistrationDaoInterface.getRegisteredCourses();

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

    @Override
    public void getSelectedCourses() {
        List<OptedCourse> courses = semesterRegistrationDaoInterface.getSelectedCourses();

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
     * Method to submit the choices of the course opted by the student
     *
     * @return boolean indicating if the choices are added successfully
     * @throws NoRegisteredCourseException
     * @throws CourseCountException
     * @throws SeatNotAvailableException
     * @throws SQLException
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

        for(OptedCourse course: primaryCourse) {
            boolean isAvailable = semesterRegistrationDaoInterface.checkAvailability(course.getCourseId());
            if (isAvailable) {
                boolean alloted = semesterRegistrationDaoInterface.allotCourse(course.getCourseId());
                if(alloted) {
                    semesterRegistrationDaoInterface.updateStudentCount(course.getCourseId());
                    courseCount += 1;
                    courseFee += course.getCourseFee();
                }
            }
        }

        for (OptedCourse course: secondaryCourse) {
            if (courseCount < 4){
                boolean isAvailable = semesterRegistrationDaoInterface.checkAvailability(course.getCourseId());
                if (isAvailable) {
                    boolean alloted = semesterRegistrationDaoInterface.allotCourse(course.getCourseId());
                    if(alloted) {
                        semesterRegistrationDaoInterface.updateStudentCount(course.getCourseId());
                        courseCount += 1;
                        courseFee += course.getCourseFee();

                    }
                }
            }
        }

        boolean submitRegistration = semesterRegistrationDaoInterface.submitRegistration(courseFee);

        String notificationContent = "You have Successfully Registered for the Semester. Please Pay fee $" + courseFee + " ASAP";
        notificationDaoInterface.sendNotification(notificationContent);

        return submitRegistration;
    }


    @Override
    public double getPendingFee() {
        return semesterRegistrationDaoInterface.getPendingFee();
    }

    @Override
    public boolean payFee(double amount) {
        boolean feePayment = semesterRegistrationDaoInterface.payFee(amount);
        if(feePayment) {
            String notificationContent = "Fee Payment Complete Welcome to the CRS.";
            notificationDaoInterface.sendNotification(notificationContent);
        }
        return feePayment;
    }



}
