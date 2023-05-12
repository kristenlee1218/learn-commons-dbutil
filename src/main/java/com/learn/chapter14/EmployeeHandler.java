package com.learn.chapter14;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.BasicRowProcessor;

/**
 * @author ：Kristen
 * @date ：2023/5/12
 * @description :
 */

public class EmployeeHandler extends BeanHandler<Employee> {

    public EmployeeHandler() {
        super(Employee.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    @Override
    public Employee handle(ResultSet rs) throws SQLException {
        Employee employee = super.handle(rs);
        employee.setName(employee.getFirst() + ", " + employee.getLast());
        return employee;
    }

    public static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("ID", "id");
        columnsToFieldsMap.put("AGE", "age");
        return columnsToFieldsMap;
    }
}

