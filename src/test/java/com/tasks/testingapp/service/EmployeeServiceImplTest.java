package com.tasks.testingapp.service;

import com.tasks.testingapp.dao.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class EmployeeServiceImplTest {


@Mock
private EmployeeRepository employeeRepository;
private AutoCloseable autoCloseable;
private EmployeeService underTest;
    @BeforeEach
    void setUp() {
       autoCloseable= MockitoAnnotations.openMocks(this);
        underTest=new EmployeeServiceImpl(employeeRepository);
    }

    @AfterEach
    void tearDown() throws Exception{

        autoCloseable.close();
    }

    @Test
    void canFindAllEmployees() {
        //when
        //
        underTest.findAll();

        //then
        //make sure employee repository was invoked
        verify(employeeRepository).findAll();
    }
}