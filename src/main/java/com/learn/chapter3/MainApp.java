package com.learn.chapter3;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ：Kristen
 * @date ：2023/5/11
 * @description : 更新
 */

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
            int updatedRecords = queryRunner.update(conn, "UPDATE employees SET age=? WHERE id=?", 33, 104);
            System.out.println(updatedRecords + " record(s) updated.");
        } finally {
            DbUtils.close(conn);
        }
    }
}

