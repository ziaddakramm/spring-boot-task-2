package com.tasks.testingapp.service;

import com.tasks.testingapp.dao.EmployeeRepository;
import com.tasks.testingapp.entity.Employee;
import com.tasks.testingapp.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {


    //@Transactional is automatically provided by JPA repository

    //DAO field
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {

        //Optional class was introduced in Java 8 as part of the java.util package
        //Handle situations where a value might be absent and provides a way to express this absence without resorting to using null.
        //Helps in avoiding NullPointerExceptions by forcing the user to explicitly handle the case where a value might be absent.
        Optional<Employee> result=employeeRepository.findById(id);

        Employee theEmployee=null;
        if(result.isPresent())
        {
            theEmployee=result.get();
            return theEmployee;
        }
        else
        {
            throw new EmployeeNotFoundException("employee id not found -"+id);
        }

    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
