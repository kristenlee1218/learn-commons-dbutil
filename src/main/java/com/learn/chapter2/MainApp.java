package com.learn.chapter2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class MainApp {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/emp";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "admin";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        try {
            int insertedRecords = queryRunner.update(conn,
                    "INSERT INTO employees(id,age,first,last)  VALUES (?,?,?,?)",
                    104, 30, "Sohan", "Kumar");
            System.out.println(insertedRecords + " record(s) inserted");
        } finally {
            DbUtils.close(conn);
        }
    }
}

