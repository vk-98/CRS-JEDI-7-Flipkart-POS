package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SqlQueries;
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

    /**
     * Returns List of courses Which are selected by the Professor based on userId
     * @param userId
     * @return
     */
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

    /**
     * Returns List of courses Which are selected by the Professor based on professorId
     * @param professorId
     * @return
     */
    public List<Course> getCoursesByProfessorId(int professorId) {
        return courseDaoInterface.getCoursesByProfessorId(professorId);
    }

    /**
     * Returns Students Enrolled in the Courses taught by professorId
     * @param profId
     * @return
     */
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

    /**
     * Returns ProfessorId corresponding to the UserId given
     * @param userId
     * @return
     */
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

    /**
     * Adds the Grade to the Student for a particular course
     * @param studentId
     * @param courseId
     * @param grade
     * @return
     */
    @Override
    public Boolean addGrade(int studentId, int courseId, String grade) {
        return gradeDaoInterface.createGrade(courseId, studentId, Integer.parseInt(grade));
    }

    /**
     * Selects a course for a professorId
     * @param professorId
     * @param courseId
     * @return
     */
    public boolean selectCourse(int professorId, int courseId) {
        if(courseDaoInterface.getProfessorIdByCourseId(courseId) == -1) {
            courseDaoInterface.setProfessorToCourse(professorId, courseId);
            return true;
        }
        return false;
    }

    /**
     * DeSelects a course for a professorId which is being selected by the same professor
     * @param professorId
     * @param courseId
     * @return
     */
    public boolean deselectCourse(int professorId, int courseId) {
        if(courseDaoInterface.getProfessorIdByCourseId(courseId) == professorId) {
            courseDaoInterface.setProfessorToCourse(-1, courseId);
            return true;
        }
        return false;
    }

    /**
     * Returns Corresponding ProfessorId of a EmailId
     * @param emailId
     * @return
     */
    @Override
    public String getProfessorIdByEmailId(String emailId) {
        int userId = userDaoInterface.getUserIdByEmail(emailId);
        return String.valueOf(getProfessorById(userId));
    }
}
