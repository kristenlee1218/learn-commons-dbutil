package com.learn.chapter12;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description : DBUtils 自定义处理程序
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
        EmployeeHandler employeeHandler = new EmployeeHandler();

        try {
            Employee emp = queryRunner.query(conn, "SELECT * FROM employees WHERE first=?",
                    employeeHandler, "Sumit");

            //Display values
            System.out.print("ID: " + emp.getId());
            System.out.print(", Age: " + emp.getAge());
            System.out.print(", Name: " + emp.getName());
        } finally {
            DbUtils.close(conn);
        }
    }
}

