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
     *
     * @param userId Unique Id of the User
     * @return Professor object
     */
    public Professor getProfessorByUserId(int userId);

    /**
     * Method to retrieve all the courses according to ProfessorId
     *
     * @param professorId Unique Id of the Professor
     * @return List of courses that professor teach
     */
    public List<Course> getCoursesByProfessorId(int professorId);

    /**
     * Method to retrieve the list of all enrolled students in a given course
     *
     * @param courseId unique Id to represent a course
     * @return List of all enrolled students in a given course
     */
    public List<Student> getEnrolledStudents(int courseId);

    /**
     * Method to add grade in the database for given student in given course
     *
     * @param studentId Unique Id of Student
     * @param courseId  unique Id to represent a course
     * @param grade     Grade assigned to student for a course
     * @return returns true if grade is added successfully or not
     */
    public Boolean addGrade(int studentId, int courseId, double grade);

    /**
     * Method to check if given student is already graded in a given course
     *
     * @param studentId Unique Id of Student
     * @param courseId  unique Id to represent a course
     * @return returns true if student is already graded
     */
    public boolean isStudentAlreadyGraded(int studentId, int courseId);

    /**
     * method to show the List of available courses
     *
     * @return List of available courses
     */
    public List<Course> getAvailableCourses();

    /**
     * method to check if given course is available or not
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the given course is available
     */
    public boolean isCourseAvailable(int courseId);

    /**
     * method to select a course
     *
     * @param professorId Unique Id for the Professor
     * @param courseId    unique Id to represent a course
     * @return returns true if course is selected successfully
     */
    public boolean selectCourse(int professorId, int courseId);

    /**
     * Method to check if course is selected or not
     *
     * @param professorId Unique Id for the Professor
     * @param courseId    unique Id to represent a course
     * @return returns true if Course is already selected by professor.
     */
    public boolean isCourseSelected(int professorId, int courseId);

    /**
     * Method to deselect the course
     *
     * @param courseId unique Id to represent a course
     * @return returns true if course is deselected successfully
     */
    public boolean deselectCourse(int courseId);

    /**
     * Method to check if a given student is enrolled in a given course
     *
     * @param studentId Unique Id of Student
     * @param courseId  unique Id to represent a course
     * @return returns true if student is already enrolled to a given course
     */
    public boolean isStudentEnrolled(int studentId, int courseId);
}
