package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;

import java.util.List;

public class ProfessorInterfaceImpl implements ProfessorInterface {
    static ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();

    /**
     * Used to Assign Grade to a Student for a particular course by professor
     * @param studentId : The Student to whom the grade is assigned
     * @param courseId : The course of the student to which the grade is given
     * @param grade : The final Grade points assigned by the professor
     * @return
     */
    @Override
    public boolean addGrad(String studentId, String courseId, String grade) {
        return professorDaoInterface.addGrade(Integer.parseInt(studentId), Integer.parseInt(courseId), grade);
    }

    /**
     * Displays all the students enrolled into the courses taught by professor
     * @param professorId - The corresponding professorId of professor
     */
    @Override
    public void viewEnrolledStudents(String professorId) {
        List<Student> students = professorDaoInterface.getEnrolledStudents(Integer.parseInt(professorId));
        System.out.println("=====Enrolled Students=====");
        System.out.println("Id | Email | Name ");
        for(Student student : students) {
            System.out.println(student.getStudentId() + " | " +
                    student.getUserEmailId() + " | " +
                    student.getUserName()
            );

        }


    }

    /**
     * It displays the Courses selected by the corresponding professor
     * @param professorId
     */
    @Override
    public void getCourses(String professorId) {
        boolean selectedCourses = false;
        List<Course> courses = professorDaoInterface.getCoursesByProfessorId(Integer.valueOf(professorId));
        for (Course course : courses) {
            if (course.getProfessorId().equals(professorId)) {
                if (!selectedCourses) {
                    selectedCourses = true;
                    System.out.println("The below are the courses you selected to teach : ");
                    System.out.println("CourseId | CourseName");
                }
                System.out.println(course.getCourseId() + " | "
                        + course.getCourseName());
            }
        }
        if (!selectedCourses) {
            System.out.println("You don't have any courses to display");
        }
    }

    /**
     * Selects a course whose courseId provided by Professor
     * @param professorId
     * @param courseId
     * @return
     */
    @Override
    public boolean selectCourse(String professorId, String courseId) {
        return professorDaoInterface.selectCourse(Integer.valueOf(professorId), Integer.valueOf(courseId));
    }

    /**
     * DeSelects a course whose courseId provided by Professor, if it is being already selected
     * @param professorId
     * @param courseId
     * @return
     */
    @Override
    public boolean deselectCourse(String professorId, String courseId) {
        return professorDaoInterface.deselectCourse(Integer.valueOf(professorId), Integer.valueOf(courseId));
    }

    /**
     * Returns the ProfessorId corresponding to the mailId
     * @param emailId
     * @return
     */
    @Override
    public String getProfessorIdByEmail(String emailId) {
        return professorDaoInterface.getProfessorIdByEmailId(emailId);
    }

}
