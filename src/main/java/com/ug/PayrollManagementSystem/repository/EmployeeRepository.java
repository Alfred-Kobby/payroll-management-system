package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmployeeNo(Integer employeeNo);
}
