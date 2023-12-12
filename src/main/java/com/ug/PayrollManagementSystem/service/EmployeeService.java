package com.ug.PayrollManagementSystem.service;

import com.ug.PayrollManagementSystem.entity.Employee;
import com.ug.PayrollManagementSystem.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> employees =  employeeRepository.findAll();
        log.info("Employees: {}", employees);
        return employees;
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Integer id){
        return employeeRepository.findByEmployeeNo(id);
    }
//    public void deleteEmployeeById(Integer id){
//        employeeRepository.deleteById(id);
//    }
}
