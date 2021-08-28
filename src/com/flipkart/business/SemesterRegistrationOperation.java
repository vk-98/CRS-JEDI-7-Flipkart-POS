package com.flipkart.business;

import com.flipkart.bean.OptedCourse;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.dao.SemesterRegistrationDaoInterface;
import com.flipkart.dao.SemesterRegistrationDaoOperation;
import com.flipkart.exceptions.*;
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
    NotificationDaoInterface notificationDaoInterface = new NotificationDaoOperation();

    private static Logger logger = Logger.getLogger(SemesterRegistrationOperation.class);


    /**
     * Method to add a primary course for a given student
     *
     * @param courseId
     * @param isPrimary
     * @return boolean indicating if the primary course is added successfully
     * @throws StudentAlreadyRegisteredForSemesterException
     * @throws MaxCoursesAlreadySelectedException
     * @throws CourseAlreadyRegisteredByStudentException
     * @throws SeatNotAvailableException
     */
    @Override
    public boolean addCourse(int courseId, int isPrimary) {

        try {
            boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();

            if (isRegistered) {
                throw new StudentAlreadyRegisteredForSemesterException();
            }

            if (isPrimary == 1) {
                int primaryCourseCount = semesterRegistrationDaoInterface.getCourseCount(1);
                if (primaryCourseCount >= 4) {
                    throw new MaxCoursesAlreadySelectedException(4, "primary");
                }
            } else {
                int secondaryCourseCount = semesterRegistrationDaoInterface.getCourseCount(0);
                if (secondaryCourseCount >= 2) {
                    throw new MaxCoursesAlreadySelectedException(2, "secondary");
                }
            }
            boolean isCourseAlreadyRegistered = semesterRegistrationDaoInterface.isCourseAlreadyRegistered(courseId);

            if (isCourseAlreadyRegistered) {
                throw new CourseAlreadyRegisteredByStudentException(courseId);
            }

            boolean isAvailable = semesterRegistrationDaoInterface.checkAvailability(courseId);

            if (!isAvailable) {
                throw new SeatNotAvailableException(courseId);
            }

            int semesterId = semesterRegistrationDaoInterface.getSemesterId();

            if (semesterId == 0) {
                boolean registered = semesterRegistrationDaoInterface.registerForSemester();
                if (registered) {
                    semesterId = semesterRegistrationDaoInterface.getSemesterId();
                }
            }

            boolean courseAdded = semesterRegistrationDaoInterface.addCourse(courseId, semesterId, isPrimary);

            if (courseAdded) {
                logger.info("Successfully added course");
                return true;
            }
        } catch (StudentAlreadyRegisteredForSemesterException e) {
            logger.info(e.getMessage());
        } catch (MaxCoursesAlreadySelectedException e) {
            logger.info(e.getMessage());
        } catch (CourseAlreadyRegisteredByStudentException e) {
            logger.info(e.getMessage());
        } catch (SeatNotAvailableException e) {
            logger.info(e.getMessage());
        }

        return false;
    }


    /**
     * Method to drop a course for a given student
     *
     * @param courseId
     * @return boolean indicating if the course is dropped successfully
     * @throws StudentAlreadyRegisteredForSemesterException
     * @throws CourseNotRegisteredByStudentException
     */
    @Override
    public boolean dropCourse(int courseId) {

        try {
            boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();

            if (isRegistered) {
                throw new StudentAlreadyRegisteredForSemesterException();
            }
            boolean isCourseRegistered = semesterRegistrationDaoInterface.isCourseAlreadyRegistered(courseId);

            if (isCourseRegistered) {
                boolean courseDropped = semesterRegistrationDaoInterface.dropCourse(courseId);
                if (courseDropped) {
                    logger.info("Course Dropped Successfully");
                    return true;
                }
            } else {
                throw new CourseNotRegisteredByStudentException(courseId);
            }
        } catch (StudentAlreadyRegisteredForSemesterException e) {
            logger.info(e.getMessage());
        } catch (CourseNotRegisteredByStudentException e) {
            logger.info(e.getMessage());
        }
        return false;
    }


    /**
     * Method to view all the courses registered by the given student
     * @throws NoRegisteredCourseException
     * @return
     */
    @Override
    public void getRegisteredCourses() {

        try {
            List<OptedCourse> courses = semesterRegistrationDaoInterface.getRegisteredCourses();

            if (courses == null || courses.size() == 0) {
               throw new NoRegisteredCourseException();
            }
            System.out.println("** Registered courses **");

            Formatter fmt = new Formatter();
            fmt.format("%20s %20s\n", "CourseId", "IsPrimary");
            for (OptedCourse course : courses) {
                fmt.format("%20s %20s\n", course.getCourseId(), course.isPrimary());
            }
            System.out.println(fmt);
        }
        catch (NoRegisteredCourseException e){
            logger.info(e.getMessage());
        }
    }

    /**
     * Method to view all the courses selected by the given student
     * @throws NoRegisteredCourseException
     * @return
     */
    @Override
    public void getSelectedCourses() {
        try {
            List<OptedCourse> courses = semesterRegistrationDaoInterface.getSelectedCourses();

            if (courses == null || courses.size() == 0) {
                throw new NoRegisteredCourseException();
            }
            System.out.println("** Registered courses **");

            Formatter fmt = new Formatter();
            fmt.format("%20s %20s\n", "CourseId", "IsPrimary");
            for (OptedCourse course : courses) {
                fmt.format("%20s %20s\n", course.getCourseId(), course.isPrimary());
            }
            System.out.println(fmt);
        }
        catch (NoRegisteredCourseException e){
            logger.info(e.getMessage());
        }
    }


    /**
     * Method to submit the choices of the course opted by the student
     *
     * @return boolean indicating if the choices are added successfully
     * @throws NoRegisteredCourseException
     * @throws RequiredCoursesSelectedException
     * @throws StudentAlreadyRegisteredForSemesterException
     */
    @Override
    public boolean submitCourseChoices() {

        try {
            boolean isRegistered = semesterRegistrationDaoInterface.getRegistrationStatus();

            if (isRegistered) {
                throw new StudentAlreadyRegisteredForSemesterException();
            }

            List<OptedCourse> courses = semesterRegistrationDaoInterface.getSelectedCourses();
            List<OptedCourse> primaryCourse = courses.stream().filter(course -> course.isPrimary()).collect(Collectors.toList());
            List<OptedCourse> secondaryCourse = courses.stream().filter(course -> !course.isPrimary()).collect(Collectors.toList());

            if (courses == null || courses.size() == 0) {
                throw new NoRegisteredCourseException();
            }

            logger.info(
                    "You have selected "
                            + primaryCourse.size()
                            + " primary courses and "
                            + secondaryCourse.size()
                            + "secondary courses."
            );
            if (courses.size() < 6) {
                throw new RequiredCoursesSelectedException();
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
            notificationDaoInterface.sendNotification(notificationContent);

            return submitRegistration;
        } catch (StudentAlreadyRegisteredForSemesterException | NoRegisteredCourseException | RequiredCoursesSelectedException e) {
            logger.info(e.getMessage());
        }
        return false;
    }


    @Override
    public double getPendingFee() {
        return semesterRegistrationDaoInterface.getPendingFee();
    }

    @Override
    public boolean payFee(double amount) {
        boolean feePayment = semesterRegistrationDaoInterface.payFee(amount);
        if (feePayment) {
            String notificationContent = "Fee Payment Complete Welcome to the CRS.";
            notificationDaoInterface.sendNotification(notificationContent);
        }
        return feePayment;
    }
}
