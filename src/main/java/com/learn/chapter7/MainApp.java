package com.learn.chapter7;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.*;
import java.util.Arrays;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description : ResultSetHandler 接口
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
        System.out.println("Connecting to database...");
        ResultSetHandler<Object[]> handler = new ResultSetHandler<Object[]>() {
            public Object[] handle(ResultSet rs) throws SQLException {
                if (!rs.next()) {
                    return null;
                }
                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                Object[] result = new Object[cols];

                for (int i = 0; i < cols; i++) {
                    result[i] = rs.getObject(i + 1);
                }
                return result;
            }
        };

        try {
            Object[] result = queryRunner.query(conn, "SELECT * FROM employees WHERE id=?",
                    handler, 103);
            //Display values
            System.out.print("Result: " + Arrays.toString(result));
        } finally {
            DbUtils.close(conn);
        }
    }
}

