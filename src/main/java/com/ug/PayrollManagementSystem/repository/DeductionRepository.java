package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.Bonus;
import com.ug.PayrollManagementSystem.entity.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeductionRepository extends JpaRepository<Deduction, Integer> {
    Deduction findByEmployeeNo(Integer employeeNo);
}
