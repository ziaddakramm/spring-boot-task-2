package com.tasks.testingapp.service;

import com.tasks.testingapp.dao.EmployeeRepository;
import com.tasks.testingapp.exception.EmployeeNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    private AutoCloseable autoCloseable;
    private EmployeeService underTest;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();


    @Before
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EmployeeServiceImpl(employeeRepository);
    }

    @After
    public void tearDown() throws Exception {

        autoCloseable.close();
    }

    @Test
    public void canFindAllEmployees() {
        //when
        //
        underTest.findAll();

        //then
        //make sure employee repository was invoked
        verify(employeeRepository).findAll();
    }


    @Test
    //whenExceptionThrown_thenAssertionSucceeds
    public void EmployeeNotFoundExceptionTest() throws EmployeeNotFoundException{
        int id = 0;

        given(employeeRepository.findById(id)).willReturn(Optional.empty());

        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            underTest.findById(id);
        });

        String expectedMessage = "employee id not found -" + id;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    //whenExceptionThrown_thenAssertionSucceeds
    public void EmployeeNotFoundExceptionRuleTest() throws EmployeeNotFoundException {
        int id = 0;
        given(employeeRepository.findById(id)).willReturn(Optional.empty());
        expectedException.expect(EmployeeNotFoundException.class);
        underTest.findById(id);
    }
}