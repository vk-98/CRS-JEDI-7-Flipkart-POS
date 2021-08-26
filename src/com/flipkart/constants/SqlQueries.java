package com.flipkart.constants;

public class SqlQueries {


    // Queries

    public static final String ADD_USER_QUERY = "insert into User(name,email,password,role, phone) values (?, ?, ?, ?, ?)";

    public static final String ADD_STUDENT = "insert into student (userId,isApproved) values (?,?)";

    public static final String GET_USER_ID = "select id from user where email = ? ";

    public static final String GET_USER_EMAIL_PASSWORD = "SELECT * FROM user WHERE email = ? AND password = ?";

    public static final String ADD_COURSE = "insert into Course(courseName, courseDescription, courseFee) values (?, ?, ?)";

    public static final String REMOVE_COURSE = "delete from Course where id = ? and studentCount = 0";

    public static final String ADD_PROFESSOR = "insert into professor (userId, department, designation) values (?,?,?)";

    public static final String LIST_APPROVAL_REQUESTS = "SELECT student.id, user.name, user.email FROM student INNER JOIN user ON student.userId=user.id WHERE student.isApproved = 0";

    public static final String APPROVE_ADDMISSION_REQUEST = "UPDATE student SET isApproved = 1 where id = ?";

    public static final String LIST_COURSES = "SELECT * FROM course";

    public static final String LIST_PROFESSORS = "SELECT professor.id, user.name, user.email, professor.department, professor.designation FROM professor INNER JOIN user ON professor.userId = user.id";

    public static final String GET_STUDENT_BY_STUDENT_ID = "SELECT * from student where id = ?";
}