package com.learn.chapter11;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description : ArrayListHandler 类
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
        //Step 2: Open a connection
        System.out.println("Connecting to database...");
        try {
            List<Object[]> result = queryRunner.query(conn, "SELECT * FROM employees", new ArrayListHandler());
            for (Object[] objects : result) {
                System.out.println(Arrays.toString(objects));
            }
        } finally {
            DbUtils.close(conn);
        }
    }
}

