package com.learn.chapter5;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description : QueryRunner 接口
 */

public class MainApp {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3366/emp";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        QueryRunner queryRunner = new QueryRunner();

        //Step 1: Register JDBC driver
        DbUtils.loadDriver(JDBC_DRIVER);

        //Step 2: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        //Step 3: Create a ResultSet Handler to handle Employee Beans
        ResultSetHandler<Employee> resultHandler = new BeanHandler<Employee>(Employee.class);

        try {
            Employee emp = queryRunner.query(conn, "SELECT * FROM employees WHERE id=?",
                    resultHandler, 103);
            //Display values
            System.out.print("ID: " + emp.getId());
            System.out.print(", Age: " + emp.getAge());
            System.out.print(", First: " + emp.getFirst());
            System.out.println(", Last: " + emp.getLast());
        } finally {
            DbUtils.close(conn);
        }
    }
}

