package com.learn.chapter6;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.*;

public class MainApp {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3366/emp";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) throws
            SQLException, InterruptedException,
            ExecutionException, TimeoutException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

        AsyncQueryRunner asyncQueryRunner = new AsyncQueryRunner(Executors.newCachedThreadPool());

        DbUtils.loadDriver(JDBC_DRIVER);
        Future<Integer> future = null;
        try {
            future = asyncQueryRunner.update(conn, "UPDATE employees SET age=? WHERE id=?", 33, 103);
            Integer updatedRecords = future.get(10, TimeUnit.SECONDS);
            System.out.println(updatedRecords + " record(s) updated.");
        } finally {
            DbUtils.close(conn);
        }
    }
}

