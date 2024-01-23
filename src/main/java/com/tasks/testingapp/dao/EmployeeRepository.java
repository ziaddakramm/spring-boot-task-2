package com.tasks.testingapp.dao;

import com.tasks.testingapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//<Entity type, Primary ke>
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{

}
