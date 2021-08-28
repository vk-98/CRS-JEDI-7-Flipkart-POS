package com.flipkart.constants;


/**
 * @author JEDI-07
 * SQL Constant
 */
public class SqlQueries {

    public static final String ADD_USER_QUERY = "insert into User(name,email,password,role, phone) values (?, ?, ?, ?, ?)";

    public static final String ADD_SEMESTER_REGISTRATION = "insert into semesterregistration(studentId,registrationStatus,feeStatus) values (?, 0, 0)";

    public static final String VIEW_COURSES_QUERY = "select * from course";

    public static final String VIEW_OPTED_COURSE_QUERY = "select * from optedCourse where studentId=?";

    public static final String UPDATE_PASSWORD = "UPDATE user SET password = ? WHERE id = ?";

    public static final String VIEW_REGISTERED_STUDENT_COURSES = "SELECT course.id, course.courseName, course.courseFee, course.professorId, course.studentCount, optedcourse.isPrimary FROM course INNER JOIN optedcourse ON course.id=optedcourse.courseId WHERE optedcourse.studentId = ? AND optedcourse.isAllotted=1";

    public static final String VIEW_SELECTED_STUDENT_COURSES = "SELECT course.id, course.courseName, course.courseFee, course.professorId, course.studentCount, optedcourse.isPrimary FROM course INNER JOIN optedcourse ON course.id=optedcourse.courseId WHERE optedcourse.studentId = ?";

    public static final String CALCULATE_FEES = "select SUM(courseFee) from course where id IN (select courseId from optedCourse where studentId=? AND isAllotted=1)";

    public static final String ADD_STUDENT = "insert into student (userId,isApproved) values (?,?)";

    public static final String GET_STUDENT_COUNT = "select count(*) from optedCourse where courseId=?";

    public static final String ADD_COURSE_STUDENT = "insert into optedCourse (courseId,semesterRegistrationId,isPrimary,isAllotted,studentId) values ( ?, ?, ?, 0, ? )";

    public static final String DROP_COURSE = "delete from optedCourse where studentId=? AND courseId = ?";

    public static final String CHECK_COURSE_STUDENT = "select * from optedCourse where studentId=? AND courseId=?";

    public static final String GET_USER_ID = "select id from user where email = ? ";

    public static final String SET_REGISTRATION_STATUS = "update semesterregistration set registrationStatus=? where studentId=?";

    public static final String GET_REGISTRATION_STATUS = "select registrationStatus from semesterregistration where studentId=?";

    public static final String SET_PAYMENT_STATUS = "update semesterregistration set feeStatus=? where studentId=?";

    public static final String GET_PAYMENT_STATUS = "select feeStatus from semesterregistration where studentId=?";

    public static final String GET_USER_EMAIL_PASSWORD = "SELECT * FROM user WHERE email = ? AND password = ?";

    public static final String ADD_COURSE = "insert into Course(courseName, courseDescription, courseFee) values (?, ?, ?)";

    public static final String SHOW_NOTIFICATIONS = "select * from notification where studentId=?";

    public static final String REMOVE_COURSE = "delete from Course where id = ? and studentCount = 0";

    public static final String ADD_PROFESSOR = "insert into professor (userId, department, designation) values (?,?,?)";

    public static final String LIST_APPROVAL_REQUESTS = "SELECT student.id, user.name, user.email FROM student INNER JOIN user ON student.userId=user.id WHERE student.isApproved = 0";

    public static final String APPROVE_ADDMISSION_REQUEST = "UPDATE student SET isApproved = 1 where id = ?";

    public static final String LIST_COURSES = "SELECT * FROM course";

    public static final String LIST_PROFESSORS = "SELECT professor.id, user.name, user.email, professor.department, professor.designation FROM professor INNER JOIN user ON professor.userId = user.id";

    public static final String GET_STUDENT_BY_EMAIL_ID = "SELECT student.id, user.name, user.email, user.phone, student.isApproved FROM user INNER JOIN student ON user.id = student.userId WHERE email = ?";

    public static final String VIEW_AVAILABLE_COURSES_PROFESSOR = "SELECT * FROM course where professorId IS NULL";

    public static final String GET_PROFESSOR_BY_USER_ID = "SELECT professor.id, user.name, user.email, professor.department, professor.designation FROM professor INNER JOIN user ON professor.userId = user.id WHERE userId = ?";

    public static final String COURSE_AVAILABLE_FOR_PROF = "SELECT professorId from course where id = ?";

    public static final String SELECT_COURSE_FOR_PROF = "UPDATE course SET professorId = ? WHERE id = ?";

    public static final String IS_COURSE_SELECTED_BY_PROF = "SELECT * FROM course WHERE professorId = ? AND id = ?";

    public static final String DELSELECT_COURSE_FOR_PROF = "UPDATE course SET professorId = NULL WHERE id = ?";

    public static final String VIEW_SELECTED_COURSES_FOR_PROF = "SELECT * FROM course WHERE professorId = ?";

    public static final String VIEW_ENROLLED_STUDENTS = "SELECT student.id, user.name, user.email, user.phone FROM student INNER JOIN user ON student.userId = user.id WHERE student.id in (SELECT studentId FROM optedcourse where isAllotted = 1 AND courseId = ?)";

    public static final String IS_STUDENT_ALREADY_GRADED = "SELECT * FROM grade WHERE studentId = ? AND courseId = ?";

    public static final String ADD_GRADE = "INSERT INTO grade(studentId, courseId, gpa) values(?, ?, ?)";

    public static final String IS_STUDENT_ENROLLED = "SELECT * FROM optedcourse WHERE studentId = ? AND courseId = ?";

    public static final String GET_STUDENT_BY_STUDENT_ID = "SELECT student.id, user.name, user.email, user.phone, student.isApproved FROM student INNER JOIN user ON student.userId = user.id WHERE student.id = ?";

    public static final String GET_COURSE_COUNT = "SELECT COUNT(*) from optedcourse where studentId = ? and isPrimary = ?";

    public static final String GET_SEMESTER_ID = "SELECT id FROM semesterregistration WHERE studentId = ?";

    public static final String CHECK_COURSE_AVAILABILITY = "SELECT studentCount FROM course where id = ?";

    public static final String ALLOT_COURSE = "UPDATE optedcourse SET isAllotted = 1 WHERE courseId = ? AND studentId = ?";

    public static final String SUBMIT_REGISTRATION = "UPDATE semesterregistration SET registrationStatus = 1, feeStatus = 0, totalFees = ? WHERE studentId = ?";

    public static final String UPDATE_STUDENT_COUNT = "UPDATE course SET studentCount = studentCount + 1 WHERE id = ?";

    public static final String SEND_NOTIFICATION = "INSERT INTO notification(studentId, notificationContent) values(?, ?)";

    public static final String GET_PENDING_FEE = "SELECT totalFees, feeStatus, registrationStatus FROM semesterregistration WHERE studentId = ?";

    public static final String PAY_FEE = "UPDATE semesterregistration SET feeStatus = 1 WHERE studentId = ?";

    public static final String GET_GRADES = "SELECT grade.courseId, course.courseName, grade.gpa from grade INNER JOIN course ON grade.courseId = course.id WHERE grade.studentId = ?";
}
