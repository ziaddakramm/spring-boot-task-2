package com.tasks.testingapp.service;

import com.tasks.testingapp.dao.EmployeeRepository;
import com.tasks.testingapp.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
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


    @Test
    //whenExceptionThrown_thenAssertionSucceeds
    public void whenExceptionThrown_thenAssertionSucceeds() {
        int id=0;
        given(employeeRepository.findById(id)).willReturn(Optional.empty());
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            underTest.findById(id);
        });

        String expectedMessage = "employee id not found -"+id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}