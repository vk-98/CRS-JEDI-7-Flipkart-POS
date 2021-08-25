package com.flipkart.constants;

public class SqlQueries {


    // Queries

    public static final String ADD_USER_QUERY = "insert into User(name,email,password,role, phone) values (?, ?, ?, ?, ?)";

    public static final String ADD_STUDENT = "insert into student (userId,isApproved) values (?,?)";

    public static final String GET_USER_ID = "select id from user where email = ? ";

    public static final String GET_USER_EMAIL_PASSWORD = "SELECT * FROM user WHERE email = ? AND password = ?";

    public static final String GET_PROFESSOR_ID = "SELECT id from professor WHERE userId = ? ";

    public static final String GET_COURSES_BY_PROFESSOR_ID = "SELECT * FROM course WHERE professorId = ? ";

    public static final String GET_STUDENT_ID_BY_PROFESSOR_ID = "SELECT studentId FROM course WHERE professorId = ? ";
    public static final String GET_USER_ID_BY_STUDENT_ID = "SELECT userId from student WHERE id = ? ";
    public static final String GET_USER_BY_USER_ID = "SELECT * from user WHERE id = ? ";
    public static final String ADD_GRADE_QUERY = "insert into grade(courseId, studentId, gpa) values (?, ?, ?)";

    public static final String GET_PROFESSOR_ID_BY_COURSE_ID = "SELECT professorId from course WHERE id = ? ";
    public static final String SET_PROFESSOR_TO_COURSE = "UPDATE course SET professorId = ? WHERE id = ?";
}