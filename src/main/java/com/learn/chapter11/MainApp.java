package com.learn.chapter11;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description :
 */

public class MainApp {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/emp";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "admin";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        QueryRunner queryRunner = new QueryRunner();
        DbUtils.loadDriver(JDBC_DRIVER);
        System.out.println("Connecting to database...");
        try {
            List<Map<String, Object>> result
                    = queryRunner.query(conn, "SELECT * FROM employees", new MapListHandler());
            System.out.println(result);
        } finally {
            DbUtils.close(conn);
        }
    }
}

