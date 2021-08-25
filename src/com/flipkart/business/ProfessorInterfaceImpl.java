package com.flipkart.business;

import com.flipkart.bean.Course;

public class ProfessorInterfaceImpl implements ProfessorInterface {
    @Override
    public boolean addGrad(String studentId, String courseId, String grade) {

        return false;
    }

    @Override
    public void viewEnrolledStudents(String professorId) {
//        for (Course course : CourseInterfaceImpl.courses) {
//            if (course.getProfessorId().equals(professorId)) {
//                //Need to retrieve Student List based on CourseId and to be displayed
//            }
//        }

    }

    @Override
    public void getCourses(String professorId) {
//        boolean selectedCourses = false;
//        for (Course course : CourseInterfaceImpl.courses) {
//            if (course.getProfessorId().equals(professorId)) {
//                if (!selectedCourses) {
//                    selectedCourses = true;
//                    System.out.println("The below are the courses you selected to teach : ");
//                    System.out.println("CourseId | CourseName");
//                }
//                System.out.println(course.getCourseId() + " | "
//                        + course.getCourseName());
//            }
//        }
//        if (!selectedCourses) {
//            System.out.println("You don't have any courses to display");
//        }
    }

    @Override
    public boolean selectCourse(String professorId, String courseId) {
//        boolean isPresent = false;
//        for (Course course : CourseInterfaceImpl.courses) {
//            if (course.getCourseId().equals(courseId)) {
//                course.setProfessorId(professorId);
//                isPresent = true;
//                break;
//            }
//        }
//        return isPresent;
        return false;
    }

    @Override
    public boolean deselectCourse(String courseId) {
//        boolean isPresent = false;
//        for (Course course : CourseInterfaceImpl.courses) {
//            if (course.getCourseId().equals(courseId)) {
//                course.setProfessorId(null);
//                isPresent = true;
//                break;
//            }
//        }
//        return isPresent;
        return false;
    }
}
