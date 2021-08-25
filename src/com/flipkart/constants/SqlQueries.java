package com.flipkart.constants;

public class SqlQueries {


    // Queries

    public static final String ADD_USER_QUERY = "insert into User(name,email,password,role, phone) values (?, ?, ?, ?, ?)";

    public static final String ADD_STUDENT = "insert into student (userId,isApproved) values (?,?)";

    public static final String GET_USER_ID = "select id from user where email = ? ";




}