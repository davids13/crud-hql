package com.davids.org.crudhql.controller;

import com.davids.org.crudhql.model.Employee;
import com.davids.org.crudhql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hql")
public class ControllerRest {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<Employee>> getAll(){

        List employeeList = new ArrayList<>();
        for (Employee e : employeeService.getAllEmployees()) {
            employeeList.add(e);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {

        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/{id}"})
    public ResponseEntity delete(@PathVariable Integer id) {

        employeeService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/",""})
    public ResponseEntity<Employee> add(@Valid @RequestBody Employee employee, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Employee addedEmployee = employeeService.save(employee);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = {"/{id}"})
    public ResponseEntity<Employee> edit (@Valid @RequestBody Employee employee, BindingResult bindingResult, @PathVariable Integer id) {

        //search for the correspondent id
        //do the verifications
        //set the update
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(employeeService.getEmployeeById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee.setId(id);
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
    }
}