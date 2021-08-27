package com.flipkart.business;

import com.flipkart.bean.Grades;
import com.flipkart.bean.Student;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.StudentNotRegisteredException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class StudentInterfaceImpl implements StudentInterface {

    private static Logger logger = Logger.getLogger(StudentInterfaceImpl.class);
    SemesterRegistrationInterface semesterRegistrationInterface= new SemesterRegistrationImpl();
    StudentDaoInterface studentDaoInterface= new StudentDaoOperation();

    /**
     * Method to register a student
     * @param studentName
     * @param studentEmailId
     * @param studentPassword
     * @param studentPhoneNo
     * @return student object or null if registration failed
     * @throws StudentNotRegisteredException
     */
    @Override
    public Student register(String studentName, String studentEmailId, String studentPassword, String studentPhoneNo) throws StudentNotRegisteredException {
        Student student = null;

        try {
            StudentDaoInterface studentDao = new StudentDaoOperation();

            student = new Student(studentName, studentEmailId, studentPassword, studentPhoneNo);
            boolean added = studentDao.addStudent(student);
            if (!added) {
                student = null;
                throw new StudentNotRegisteredException(studentName);
            }
        } catch (StudentNotRegisteredException ex) {
            logger.info(logger.getClass());
            logger.error(ex.getStudentName() + " is not registered.");
        }
        return student;
    }

    @Override
    public String registerForSemester(String studentId) {
        return null;
    }

    @Override
    public void viewGrades(int studentId) throws SQLException {
       if(!semesterRegistrationInterface.getPaymentStatus(studentId))
       {
          logger.error("Do registration and payment for semester");
           return;
       }

       List<Grades> grades= studentDaoInterface.getGrades(studentId);

       if(grades.size()<6)
       {
          logger.info("Grades are yet to be added!!!");
           return;
       }

        System.out.println("Here are your grades!!!");

        System.out.println("CourseId | Gpa");
        double sum=0;
        for (Grades grade:grades) {
            System.out.println("    "+grade.getRegisterdCourseId()+"     "+grade.getGpa());
            sum=sum +grade.getGpa();
        }

        System.out.println("Your cumulative Gpa is :  "+sum);

    }

    @Override
    public boolean payFee(String studentId, String studentRegistrationId, double amount) {
        return false;
    }
}