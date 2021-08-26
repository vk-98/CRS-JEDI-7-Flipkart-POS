package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;

import java.util.List;

public class ProfessorInterfaceImpl implements ProfessorInterface {

    public static Professor professor = null;
    ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();

    public void getProfessor() {
        professor = professorDaoInterface.getProfessorByUserId(UserInterfaceImpl.user.getId());
    }

    @Override
    public void addGrade(int studentId, int courseId, double grade) {
        boolean courseSelected = professorDaoInterface.IsCourseSelected(courseId);
        if(!courseSelected){
            System.out.println("Invalid Course ID");
            return;
        }
        boolean isStudentEnrolled = professorDaoInterface.IsStudentEnrolled(studentId, courseId);
        if(!isStudentEnrolled) {
            System.out.println("Student Not Enrolled in the course");
            return;
        }
        boolean studentGraded = professorDaoInterface.IsStudentAlreadyGraded(studentId, courseId);
        if(studentGraded) {
            System.out.println("Student Is Already Graded");
        } else {
            boolean graded = professorDaoInterface.addGrade(studentId, courseId, grade);
            if (graded) {
                System.out.println("Grades Added Successfully");
            }
        }
    }

    @Override
    public void viewEnrolledStudents(int courseId) {
        boolean courseSelected = professorDaoInterface.IsCourseSelected(courseId);
        System.out.println("courseSelected" + courseSelected);
        if(courseSelected) {
            List<Student> students = professorDaoInterface.getEnrolledStudents(courseId);
            if(students==null || students.size()==0) {
                System.out.println("No Students enrolled in courseId: " + courseId + ".");
            }else {
                System.out.println("Student ID | StudentName | StudentEmail | StudentPhone");
                for(Student student: students) {
                    System.out.println(
                            student.getStudentId()
                            + " | "
                            + student.getUserEmailId()
                            + " | "
                            + student.getUserEmailId()
                            + " | "
                            + student.getPhoneNo()
                    );
                }
            }
        } else {
            System.out.println("Enter Correct Course Id");
        }
    }

    @Override
    public void viewSelectedCourses() {
        List<Course> courses = professorDaoInterface.getCoursesByProfessorId();
        if (courses == null || courses.size() == 0) {
            System.out.println("No Courses available");
        } else {
            System.out.println("Course Id | CourseName | Course Description | CourseFee | Student Count");
            for (Course c : courses) {
                System.out.println(
                        c.getCourseId()
                                + " | "
                                + c.getCourseName()
                                + " | "
                                + c.getCourseDescription()
                                + " | "
                                + c.getCourseFee()
                                + " | "
                                + c.getStudentCount()

                );
            }
        }
    }

    @Override
    public void selectCourse(int courseId) {

        boolean courseAvailable = professorDaoInterface.IsCourseAvailable(courseId);
        if (courseAvailable) {
            boolean courseSelected = professorDaoInterface.selectCourse(courseId);
            if (courseSelected) {
                System.out.println("Course with courseId " + courseId + " selected.");
                return;
            }
        }
        System.out.println("Course with courseId " + courseId + " cannot be selected.");

    }

    @Override
    public void deselectCourse(int courseId) {
        boolean isCourseSelected = professorDaoInterface.IsCourseSelected(courseId);
        System.out.println(isCourseSelected);
        if (isCourseSelected) {
            boolean courseDeselected = professorDaoInterface.deselectCourse(courseId);
            if (courseDeselected) {
                System.out.println("Course with course Id: " + courseId + " deselected successfully.");
                return;
            }
        }
        System.out.println("Course with course Id: " + courseId + " cannot be deselected.");
    }

    @Override
    public void viewAvailableCourses() {
        List<Course> courses = professorDaoInterface.viewAvailableCourses();
        if (courses == null || courses.size() == 0) {
            System.out.println("No Courses available");
        } else {
            System.out.println("Course Id | CourseName | Course Description | CourseFee | Student Count");
            for (Course c : courses) {
                System.out.println(
                        c.getCourseId()
                                + " | "
                                + c.getCourseName()
                                + " | "
                                + c.getCourseDescription()
                                + " | "
                                + c.getCourseFee()
                                + " | "
                                + c.getStudentCount()

                );
            }
        }
    }
}
