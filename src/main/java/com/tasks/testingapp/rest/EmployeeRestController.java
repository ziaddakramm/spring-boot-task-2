package com.tasks.testingapp.rest;


import com.tasks.testingapp.entity.Employee;
import com.tasks.testingapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //Service field
    private EmployeeService employeeService;


    //Constructor injection for
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> getAll() {

        return employeeService.findAll();
    }


    //expose GET "/employees" and return a list of employees/{id}
    // Path variable and function parameter should be named the same
    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable int id) {
        //Find employee
        Employee employee = employeeService.findById(id);

        //Return employee if found
        return employee;
    }




    @PostMapping("employees")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        //In case the id is passed in json
        employee.setId(0);

        Employee dbEmployee=employeeService.save(employee);

        return dbEmployee;
    }


    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee employee)
    {
        Employee dbEmployee=employeeService.save(employee);

        return dbEmployee;
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable int id)
    {
        Employee tempEmployee= employeeService.findById(id);

        employeeService.deleteById(id);

        return "Deleted employee with id: " + id;
    }


}
