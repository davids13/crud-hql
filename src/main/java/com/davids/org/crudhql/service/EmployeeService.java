package com.davids.org.crudhql.service;

import com.davids.org.crudhql.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    Employee save (Employee employee);

    void delete (Integer id);
}