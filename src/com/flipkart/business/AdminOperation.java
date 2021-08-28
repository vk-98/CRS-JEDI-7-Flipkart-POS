package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.exceptions.StudentAlreadyApprovedException;
import com.flipkart.exceptions.StudentNotFoundException;
import org.apache.log4j.Logger;

import java.util.Formatter;
import java.util.List;

public class AdminOperation implements AdminInterface {
    private static Logger logger = Logger.getLogger(AdminOperation.class);
    AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
    StudentDaoInterface studentDaoInterface = new StudentDaoOperation();

    @Override
    public void addCourse(String courseName, String courseDescription, double courseFee) {
        Course course = new Course(courseName, courseDescription, courseFee);
        adminDaoInterface.addCourse(course);
        logger.info("Course Added Successfully");
    }

    @Override
    public void removeCourse(int courseId) {
        try {
            int rowsAffected = adminDaoInterface.removeCourse(courseId);
            if (rowsAffected == 0) {
                throw new CourseNotFoundException(courseId);
            }
            logger.info("Course removed successfully");
        } catch (CourseNotFoundException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void approveStudentRequest(int studentId) {

        try {
            Student st = studentDaoInterface.getStudentByStudentId(studentId);
            if (st == null) {
                throw new StudentNotFoundException(studentId);
            }
            if (st.isApproved()) {
                throw new StudentAlreadyApprovedException(studentId);
            }
            boolean isApproved = adminDaoInterface.approveStudent(studentId);
            if (isApproved) {
                logger.info("Student Addmission Request approved");
            }
        } catch (StudentNotFoundException e) {
            logger.info(e.getMessage());
        } catch (StudentAlreadyApprovedException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void addProfessor(String name, String emailId, String password, String phoneNo, String department, String designation) {
        boolean added = adminDaoInterface.addProfessor(name, emailId, password, phoneNo, department, designation);
        if (added) logger.info("Professor created successfully.");
        else logger.info("Professor not added.");
    }

    @Override
    public void listAdmissionRequests() {
        List<Student> requests = adminDaoInterface.viewPendingAdmissions();
        if (requests == null || requests.size() == 0) {
            logger.info("No pending addmission requests");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%30s %30s %30s\n", "StudentID", "StudentName", "StudentEmailId");
            for (Student st : requests) {
                fmt.format("%30s %30s %30s\n",st.getStudentId(), st.getUserName(), st.getUserEmailId());
            }
            System.out.println(fmt);
        }
    }

    @Override
    public void viewProfessors() {
        List<Professor> professors = adminDaoInterface.viewProfessors();
        if (professors == null || professors.size() == 0) {
            logger.info("No Professors registered");
        } else {
            Formatter fmt = new Formatter();
            fmt.format("%30s %30s %30s %30s %30s\n", "ProfId", "ProfName","ProfEmail ID", "ProfDepartment", "ProfDesignation");
            for (Professor p : professors) {
                fmt.format("%30s %30s %30s %30s %30s\n",p.getProfessorId(),p.getUserName(), p.getUserEmailId(),p.getDepartment(),p.getDesignation());
            }
            System.out.println(fmt);
        }
    }
}