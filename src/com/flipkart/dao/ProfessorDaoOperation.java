package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.SqlQueries;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDaoOperation implements ProfessorDaoInterface {
    static Connection con = DBUtil.getConnection();
    static CourseDaoInterface courseDaoInterface = new CourseDaoOperation();
    static StudentDaoInterface studentDaoInterface = new StudentDaoOperation();
    static GradeDaoInterface gradeDaoInterface = new GradeDaoOperation();
    static UserDaoInterface userDaoInterface = new UserDaoOperation();

    @Override
    public List<Course> getCoursesByProfessor(int userId) {
        try {

            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_PROFESSOR_ID);
            ps.setInt(1, userId);
            ResultSet result = ps.executeQuery();
            int professorId = -1;
            if (result.next()) {
                professorId = result.getInt("id");
            }
            return courseDaoInterface.getCoursesByProfessorId(professorId);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<Course> getCoursesByProfessorId(int professorId) {
        return courseDaoInterface.getCoursesByProfessorId(professorId);
    }

    @Override
    public List<Student> getEnrolledStudents(int profId) {
        List<Integer> studentIds = courseDaoInterface.getStudentIdsByProfessorId(profId);
        List<Student> students = new ArrayList<Student>();
        for (int studentId : studentIds) {
            System.out.println("StudentId " + studentId);
            students.add(studentDaoInterface.getStudent(studentId));
        }
        return students;
    }

    @Override
    public int getProfessorById(int userId) {
        try {
            PreparedStatement ps = con.prepareStatement(SqlQueries.GET_PROFESSOR_ID);
            ps.setInt(1, userId);
            ResultSet result = ps.executeQuery();
            int professorId = -1;
            if (result.next()) {
                professorId = result.getInt("id");
            }
            return professorId;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Boolean addGrade(int studentId, int courseId, String grade) throws GradeNotAddedException {
        return gradeDaoInterface.createGrade(courseId, studentId, Integer.parseInt(grade));
    }

    public boolean selectCourse(int professorId, int courseId)  {
        try{
        if (courseDaoInterface.getProfessorIdByCourseId(courseId) == -1) {
            courseDaoInterface.setProfessorToCourse(professorId, courseId);
            return true;
        }
        }catch(CourseNotFoundException e){
            System.out.println(new CourseNotFoundException(String.valueOf(courseId)));
        }
        return false;
    }

    public boolean deselectCourse(int professorId, int courseId) throws CourseNotFoundException {
        if(courseDaoInterface.getProfessorIdByCourseId(courseId) == professorId) {
            courseDaoInterface.setProfessorToCourse(-1, courseId);
            return true;
        }
        return false;
    }

    @Override
    public String getProfessorIdByEmailId(String emailId) {
        int userId = userDaoInterface.getUserIdByEmail(emailId);
        return String.valueOf(getProfessorById(userId));
    }
}
