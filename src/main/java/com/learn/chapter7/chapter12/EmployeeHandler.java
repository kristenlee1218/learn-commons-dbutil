package com.learn.chapter7.chapter12;

import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description :
 */

public class EmployeeHandler extends BeanHandler<Employee> {

    public EmployeeHandler() {
        super(Employee.class);
    }

    @Override
    public Employee handle(ResultSet rs) throws SQLException {
        Employee employee = super.handle(rs);
        employee.setName(employee.getFirst() + ", " + employee.getLast());
        return employee;
    }
}
