package com.restapiexample.service;

import com.restapiexample.payload.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    public EmployeeDto saveEmployee(int person_id, EmployeeDto employeeDto);

    public List<EmployeeDto> getAllEmployees(int person_id);

    public EmployeeDto getEmployee(int person_id,int mgrid);

    public void deleteEmployee(int person_id,int mgr_id);


}
