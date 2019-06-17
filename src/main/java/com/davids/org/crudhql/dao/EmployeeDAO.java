package com.davids.org.crudhql.dao;

import com.davids.org.crudhql.model.Employee;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    void save(Employee employee);

    void delete(Integer id);
}