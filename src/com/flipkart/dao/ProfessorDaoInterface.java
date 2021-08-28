package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * @author JEDI-07
 * Professor Dao Interface
 */
public interface ProfessorDaoInterface {
    /**
     * Method to retrieve Professor Details using userId
     * @param userId
     * @return Professor Details
     */
    public Professor getProfessorByUserId(int userId);

    /**
     * Method to retrieve all the courses according to ProfessorId
     * @param professorId
     * @return List of courses
     */
    public List<Course> getCoursesByProfessorId(int professorId);

    /**
     * Method to retrieve the list of all enrolled students in a given course
     * @param courseId
     * @return List of all enrolled students in a given course
     */
    public List<Student> getEnrolledStudents(int courseId);

    /**
     * Method to add grade in the database for given student in given course
     * @param studentId
     * @param courseId
     * @param grade
     * @return if grade is added successfully or not
     */
    public Boolean addGrade(int studentId, int courseId, double grade);

    /**
     * Method to check if given student is already graded in a given course
     * @param studentId
     * @param courseId
     */
    public boolean isStudentAlreadyGraded(int studentId, int courseId);

    /**
     * method to show the List of available courses
     * @return List of available courses
     */
    public List<Course> getAvailableCourses();

    /**
     * method to check if given course is available or not
     * @param courseId
     * @return availability of the given course
     */
    public boolean isCourseAvailable(int courseId);

    /**
     * method to select a course
     * @param professorId
     * @param courseId
     * @return isCourseSelected
     */
    public boolean selectCourse(int professorId, int courseId);

    /**
     * Method to check if course is selected or not
     * @param professorId
     * @param courseId
     * @return is Course already selected by professor.
     */
    public boolean isCourseSelected(int professorId, int courseId);

    /**
     * Method to deselect the course
     * @param courseId
     */
    public boolean deselectCourse(int courseId);

    /**
     * Method to check if a given student is enrolled in a given course
     * @param studentId
     * @param courseId
     */
    public boolean isStudentEnrolled(int studentId, int courseId);
}
