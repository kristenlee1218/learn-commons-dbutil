package com.learn.chapter2;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainApp {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3366/emp";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        try {
            int insertedRecords = queryRunner.update(conn, "INSERT INTO employees(id,age,first,last)  VALUES (?,?,?,?)", 104, 30, "Sohan", "Kumar");
            System.out.println(insertedRecords + " record(s) inserted");
        } finally {
            DbUtils.close(conn);
        }
    }
}

