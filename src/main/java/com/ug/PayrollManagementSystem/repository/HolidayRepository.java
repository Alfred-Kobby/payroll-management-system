package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.Bonus;
import com.ug.PayrollManagementSystem.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    Holiday findByEmployeeNo(Integer employeeNo);
}
