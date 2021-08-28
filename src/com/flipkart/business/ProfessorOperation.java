package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exceptions.CourseNotAvailableForProfessorException;
import com.flipkart.exceptions.CourseNotSelectedException;
import com.flipkart.exceptions.StudentAlreadyGradedException;
import com.flipkart.exceptions.StudentNotEnrolledInCourseException;
import org.apache.log4j.Logger;

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
     * @throws CourseNotSelectedException
     * @throws StudentNotEnrolledInCourseException
     * @throws StudentAlreadyGradedException
     */
    @Override
    public boolean addGrade(int studentId, int courseId, double grade) {
        try {
            boolean courseSelected = professorDaoInterface.isCourseSelected(professor.getProfessorId(), courseId);
            if (!courseSelected) {
                throw new CourseNotSelectedException(courseId);
            }
            boolean isStudentEnrolled = professorDaoInterface.isStudentEnrolled(studentId, courseId);
            if (!isStudentEnrolled) {
                throw new StudentNotEnrolledInCourseException(courseId, studentId);
            }
            boolean studentGraded = professorDaoInterface.isStudentAlreadyGraded(studentId, courseId);
            if (studentGraded) {
                throw new StudentAlreadyGradedException(studentId, courseId);
            }
            return professorDaoInterface.addGrade(studentId, courseId, grade);
        } catch (CourseNotSelectedException e) {
            logger.info(e.getMessage());
        } catch (StudentNotEnrolledInCourseException e) {
            logger.info(e.getMessage());
        } catch (StudentAlreadyGradedException e) {
            logger.info(e.getMessage());
        }
        return false;
    }

    /**
     * Method to view all enrolled students in a particular course
     *
     * @param courseId
     * @return list of students
     * @throws CourseNotSelectedException
     */
    @Override
    public List<Student> getEnrolledStudents(int courseId) {
        try {
            boolean courseSelected = professorDaoInterface.isCourseSelected(professor.getProfessorId(), courseId);
            if (!courseSelected) {
                throw new CourseNotSelectedException(courseId);
            }

            return professorDaoInterface.getEnrolledStudents(courseId);

        } catch (CourseNotSelectedException e) {
            logger.info(e.getMessage());
        }
        return null;
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
     * @throws CourseNotAvailableForProfessorException
     */
    @Override
    public boolean selectCourse(int courseId) {

        try {
            boolean courseAvailable = professorDaoInterface.isCourseAvailable(courseId);
            if (!courseAvailable) {
                throw new CourseNotAvailableForProfessorException(courseId);
            }

            boolean isCourseSelected = professorDaoInterface.selectCourse(professor.getProfessorId(), courseId);
            if (isCourseSelected) {
                return true;
            }

        } catch (CourseNotAvailableForProfessorException e) {
            logger.info(e.getMessage());
        }
        return false;

    }

    /**
     * method to deselect the course
     *
     * @param courseId
     * @return isCourseDeselected
     * @throws CourseNotSelectedException
     */
    @Override
    public boolean deselectCourse(int courseId) {

        try {
            boolean isCourseSelected = professorDaoInterface.isCourseSelected(professor.getProfessorId(), courseId);
            if (!isCourseSelected) {
                throw new CourseNotSelectedException(courseId);
            }
                boolean isCourseDeselected = professorDaoInterface.deselectCourse(courseId);
                if (isCourseDeselected) {
                    return true;
                }

        }
        catch (CourseNotSelectedException e){
            logger.info(e.getMessage());
        }
        return false;

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
