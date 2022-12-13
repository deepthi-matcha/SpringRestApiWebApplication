package com.restapiexample.repository;

import com.restapiexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee> findAllByPersonsId(int personId);


}
