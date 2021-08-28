package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exceptions.CourseNotSelectedExcpetion;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;

/**
 * @author JEDI-07
 * Implementation of professor interface
 */
public class ProfessorOperation implements ProfessorInterface {
    private static Logger logger = Logger.getLogger(ProfessorOperation.class);
    public static Professor professor = null;
    ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();

    /**
     * method to retrieve Professor Details
     */
    public void getProfessor() {
        professor = professorDaoInterface.getProfessorByUserId(UserOperation.user.getId());
    }

    /**
     * Method to add Grade in the database
     *
     * @param studentId
     * @param courseId
     * @param grade
     * @return graded
     */
    @Override
    public boolean addGrade(int studentId, int courseId, double grade) {
        try {
            boolean courseSelected = professorDaoInterface.isCourseSelected(professor.getProfessorId(), courseId);
            if (!courseSelected) {
                throw new CourseNotSelectedExcpetion(courseId);
            }
            boolean isStudentEnrolled = professorDaoInterface.isStudentEnrolled(studentId, courseId);
            if (!isStudentEnrolled) {
                logger.info("Student Not Enrolled in the course");
                return false;
            }
            boolean studentGraded = professorDaoInterface.isStudentAlreadyGraded(studentId, courseId);
            if (studentGraded) {
                logger.info("Student Is Already Graded");
                return false;
            }
            return professorDaoInterface.addGrade(studentId, courseId, grade);
        } catch (CourseNotSelectedExcpetion e) {
            logger.info(e.getMessage());
        }
        return false;
    }

    /**
     * Method to view all enrolled students in a particular course
     *
     * @param courseId
     * @return list of students
     */
    @Override
    public List<Student> getEnrolledStudents(int courseId) {
        // Exception
        boolean courseSelected = professorDaoInterface.isCourseSelected(professor.getProfessorId(), courseId);
        if (courseSelected) {
            return professorDaoInterface.getEnrolledStudents(courseId);
        }
        return null;
//            logger.info("Enter Correct Course Id");
    }

    /**
     * method to view all selected course
     *
     * @return list of selected courses
     */
    @Override
    public List<Course> getSelectedCourses() {
        return professorDaoInterface.getCoursesByProfessorId(professor.getProfessorId());
    }

    /**
     * method to select the course to teach
     *
     * @param courseId
     * @return isCourseSelected
     */
    @Override
    public boolean selectCourse(int courseId) {
        // Exception
        boolean courseAvailable = professorDaoInterface.isCourseAvailable(courseId);
        if (courseAvailable) {
            boolean isCourseSelected = professorDaoInterface.selectCourse(professor.getProfessorId(), courseId);
            if (isCourseSelected) {
                return true;
            }
        }
        return false;
        //logger.info("Course with course Id: " + courseId + " cannot be selected.");
    }

    /**
     * method to deselect the course
     *
     * @param courseId
     * @return isCourseDeselected
     */
    @Override
    public boolean deselectCourse(int courseId) {
        // Exception
        boolean isCourseSelected = professorDaoInterface.isCourseSelected(professor.getProfessorId(), courseId);
        if (isCourseSelected) {
            boolean isCourseDeselected = professorDaoInterface.deselectCourse(courseId);
            if (isCourseDeselected) {
                return true;
            }
        }
        return false;
        //logger.info("Course with course Id: " + courseId + " cannot be deselected.");
    }

    /**
     * method to view all available courses
     *
     * @return list of courses.
     */
    @Override
    public List<Course> getAvailableCourses() {
        return professorDaoInterface.getAvailableCourses();
    }
}
