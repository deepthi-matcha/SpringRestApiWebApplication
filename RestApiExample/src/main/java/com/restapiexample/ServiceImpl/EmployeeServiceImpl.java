package com.restapiexample.ServiceImpl;

import com.restapiexample.Exception.ApiException;
import com.restapiexample.Exception.EmployeeNotFound;
import com.restapiexample.Exception.PersonNotFound;
import com.restapiexample.entity.Employee;
import com.restapiexample.entity.Persons;
import com.restapiexample.payload.EmployeeDto;
import com.restapiexample.repository.EmployeeRepository;
import com.restapiexample.repository.PersonsRepository;
import com.restapiexample.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private PersonsRepository personsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(int person_id, EmployeeDto employeeDto) {
        Persons persons=personsRepository.findById(String.valueOf(person_id)).orElseThrow(()->new PersonNotFound(String.format("Person ID %d not found ",person_id)));
        Employee employee=modelMapper.map(employeeDto,Employee.class);
        employee.setPersons(persons);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee,EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees(int person_id) {
        Persons persons=personsRepository.findById(String.valueOf(person_id)).orElseThrow(()->new PersonNotFound(String.format("Person ID %d not found ",person_id)));
        List<Employee> employees=employeeRepository.findAllByPersonsId(person_id);
        return employees.stream().map(employee -> modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
    }

    public EmployeeDto getEmployee(int person_id,int mgr_id){
        Persons persons=personsRepository.findById(String.valueOf(person_id)).orElseThrow(()->new PersonNotFound(String.format("Person ID %d not found ",person_id)));
        Employee employee=employeeRepository.findById(mgr_id).orElseThrow(()->new EmployeeNotFound(String.format("MgrId %d not found",mgr_id)));
        if(persons.getId()!=employee.getPersons().getId()){
            throw new ApiException(String.format("Manager Id is not belongs to Person Id %d",mgr_id,person_id));
        }
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(int person_id,int mgr_id) {
        Persons persons=personsRepository.findById(String.valueOf(person_id)).orElseThrow(()->new PersonNotFound(String.format("Person ID %d not found ",person_id)));
        Employee employee=employeeRepository.findById(mgr_id).orElseThrow(()->new EmployeeNotFound(String.format("MgrId %d not found",mgr_id)));
        if(persons.getId()!=employee.getPersons().getId()){
            throw new ApiException(String.format("Manager Id is not belongs to Person Id %d",mgr_id,person_id));
        }
        employeeRepository.deleteById(person_id);

    }


}
