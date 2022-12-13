package com.restapiexample.controller;

import com.restapiexample.entity.Employee;
import com.restapiexample.payload.EmployeeDto;
import com.restapiexample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //save employee
    @PostMapping("/{person_id}/employee")
    public ResponseEntity<EmployeeDto> saveEmployee(@PathVariable(name="person_id") int id,@RequestBody EmployeeDto employeeDto){
            return new ResponseEntity<>(employeeService.saveEmployee(id,employeeDto), HttpStatus.CREATED);
    }

    //get all employee
    @GetMapping("/{person_id}/employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@PathVariable (name="person_id") int id ){
        return new ResponseEntity<>(employeeService.getAllEmployees(id),HttpStatus.OK);
    }


    //get employee by id
    @GetMapping("{person_id}/employee/{mgrid}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable(name="person_id")int id,@PathVariable(name="managerid") int mgr_id){
        return new ResponseEntity<>(employeeService.getEmployee(id,mgr_id),HttpStatus.OK);
    }

    //delete by id
    @GetMapping("{persons}/employee/{mgrid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name="person_id")int id,@PathVariable(name="mgr_id") int mgr_id){
        employeeService.deleteEmployee(id,mgr_id);
        return new ResponseEntity<>("Person deleted successfully",HttpStatus.OK);
    }

}
