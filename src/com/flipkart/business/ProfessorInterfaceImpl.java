package com.flipkart.business;

import com.flipkart.application.CRSApplicationClient;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;

public class ProfessorInterfaceImpl implements ProfessorInterface {
    private static Logger logger = Logger.getLogger(CRSApplicationClient.class);
    public static Professor professor = null;
    ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();

    public void getProfessor() {
        professor = professorDaoInterface.getProfessorByUserId(UserInterfaceImpl.user.getId());
    }

    @Override
    public void addGrade(int studentId, int courseId, double grade) {
        boolean courseSelected = professorDaoInterface.IsCourseSelected(courseId);
        if (!courseSelected) {
            logger.info("Invalid Course ID");
            return;
        }
        boolean isStudentEnrolled = professorDaoInterface.IsStudentEnrolled(studentId, courseId);
        if (!isStudentEnrolled) {
            logger.info("Student Not Enrolled in the course");
            return;
        }
        boolean studentGraded = professorDaoInterface.IsStudentAlreadyGraded(studentId, courseId);
        if (studentGraded) {
            logger.info("Student Is Already Graded");
        } else {
            boolean graded = professorDaoInterface.addGrade(studentId, courseId, grade);
            if (graded) {
                logger.info("Grades Added Successfully");
            }
        }
    }

    @Override
    public void viewEnrolledStudents(int courseId) {
        boolean courseSelected = professorDaoInterface.IsCourseSelected(courseId);
        if (courseSelected) {
            List<Student> students = professorDaoInterface.getEnrolledStudents(courseId);
            if (students == null || students.size() == 0) {
                logger.info("No Students enrolled in courseId: " + courseId + ".");
            } else {
                Formatter fmt = new Formatter();
                fmt.format("%30s %30s %30s %30s\n", "StudentId", "StudentName", "StudentEmailID", "StudentPhone");
                for (Student student : students) {
                    fmt.format("%30s %30s %30s %30s\n", student.getStudentId(), student.getUserEmailId(), student.getUserEmailId(), student.getPhoneNo());

                }
                System.out.println(fmt);
            }
        } else {
            logger.info("Enter Correct Course Id");
        }
    }

    @Override
    public void viewSelectedCourses() {
        List<Course> courses = professorDaoInterface.getCoursesByProfessorId();
        if (courses == null || courses.size() == 0) {
            logger.info("No Courses available");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%30s %30s %30s %30s %30s\n", "CourseId", "CourseName", "CourseDescription", "CourseFee", "StudentCount");
            for (Course c : courses) {
                fmt.format("%30s %30s %30s %30s %30s\n", c.getCourseId(), c.getCourseName(), c.getCourseDescription(), c.getCourseFee(), c.getStudentCount());
            }
            System.out.println(fmt);
        }
    }

    @Override
    public void selectCourse(int courseId) {

        boolean courseAvailable = professorDaoInterface.IsCourseAvailable(courseId);
        if (courseAvailable) {
            boolean courseSelected = professorDaoInterface.selectCourse(courseId);
            if (courseSelected) {
                logger.info("Course with courseId " + courseId + " selected.");
                return;
            }
        }
        logger.info("Course with courseId " + courseId + " cannot be selected.");

    }

    @Override
    public void deselectCourse(int courseId) {
        boolean isCourseSelected = professorDaoInterface.IsCourseSelected(courseId);
        System.out.println(isCourseSelected);
        if (isCourseSelected) {
            boolean courseDeselected = professorDaoInterface.deselectCourse(courseId);
            if (courseDeselected) {
                logger.info("Course with course Id: " + courseId + " deselected successfully.");
                return;
            }
        }
        logger.info("Course with course Id: " + courseId + " cannot be deselected.");
    }

    @Override
    public void viewAvailableCourses() {
        List<Course> courses = professorDaoInterface.viewAvailableCourses();
        if (courses == null || courses.size() == 0) {
            logger.info("No Courses available");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%30s %30s %30s %30s %30s\n", "CourseId", "CourseName", "CourseDescription", "CourseFee", "StudentCount");
            for (Course c : courses) {
                fmt.format("%30s %30s %30s %30s %30s\n", c.getCourseId(), c.getCourseName(), c.getCourseDescription(), c.getCourseFee(), c.getStudentCount());
            }
            System.out.println(fmt);
        }
    }
}
