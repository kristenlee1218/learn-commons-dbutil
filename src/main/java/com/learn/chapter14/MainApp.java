package com.learn.chapter14;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

import static com.learn.chapter14.CustomDataSource.JDBC_DRIVER;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description : DBUtils 使用 DataSource
 */

public class MainApp {
    public static void main(String[] args) throws SQLException {

        DbUtils.loadDriver(JDBC_DRIVER);
        QueryRunner queryRunner = new QueryRunner(CustomDataSource.getInstance());
        ResultSetHandler<Employee> resultHandler = new BeanHandler<>(Employee.class);

        Employee emp = queryRunner.query("SELECT * FROM employees WHERE id=?", resultHandler, 103);
        System.out.print("ID: " + emp.getId());
        System.out.print(", Age: " + emp.getAge());
        System.out.print(", First: " + emp.getFirst());
        System.out.println(", Last: " + emp.getLast());
    }
}


