package com.flipkart.constants;

public class SqlQueries {


    // Queries

    public static final String ADD_USER_QUERY = "insert into User(name,email,password,role, phone) values (?, ?, ?, ?, ?)";
    public static final String ADD_SEMESTER_REGISTRATION = "insert into semesterregistration(studentId,registrationStatus,feeStatus) values (?, ?, ?)";
    public static final String VIEW_COURSES_QUERY = "select * from course";
    public static final String VIEW_OPTED_COURSE_QUERY = "select * from optedCourse where studentId=?";
    public static final String VIEW_REGISTERED_COURSES_QUERY = "select * from course where id IN (select courseId from optedCourse where studentId=? AND isAllotted=1)";
    public static final String CALCULATE_FEES= "select SUM(courseFee) from course where id IN (select courseId from optedCourse where studentId=? AND isAllotted=1)";

    public static final String ADD_STUDENT = "insert into student (userId,isApproved) values (?,?)";
    public static final String GET_STUDENT_COUNT= "select count(*) from optedCourse where courseId=?";

    public static final String ADD_COURSE_STUDENT="insert into optedCourse (courseId,semesterRegistrationId,isPrimary,isAllotted,studentId) values ( ? , ?, ?,?,? )";
    public static final String DROP_COURSE= "delete from optedCourse where studentId=? AND courseId = ?";

    public static final String GET_STUDENT_ID= "select id from student where userId = ? ";
    public static final String GET_USER_ID = "select id from user where email = ? ";
    public static final String GET_SEM_REGISTRATION_ID= "select id from semesterregistration where studentId= ?";

    public static final String GET_USER_EMAIL_PASSWORD = "SELECT * FROM user WHERE email = ? AND password = ?";

    public static final String SEND_NOTIFICATION= "insert into notification(notificationName,studentId) values(?,?)";
    public static final String SHOW_NOTIFICATIONS= "select * from notification where studentId=?";


}