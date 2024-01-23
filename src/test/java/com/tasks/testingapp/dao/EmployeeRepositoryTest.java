package com.tasks.testingapp.dao;

import com.tasks.testingapp.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@ExtendWith(SpringExtension.class)


//Effective for custom queries when using jpa repository
@DataJpaTest
public class EmployeeRepositoryTest {


    @Autowired
    private EmployeeRepository underTest;

    @BeforeEach
    void setUp() {
    underTest.save(new Employee("z","a","k"));

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldGetEmployeeById()
    {
        //given
        int employeeId=1;
        //when
        Optional<Employee> employee=underTest.findById(employeeId);
        //then
        assertThat(employee).isPresent();
    }
}