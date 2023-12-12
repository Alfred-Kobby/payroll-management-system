package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.SickLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDate;
import java.util.List;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Integer> {
    List<SickLeave> findByEmployeeNo(Integer employeeNo);
    SickLeave findByEmployeeNoAndStartDate(Integer employeeNo, LocalDate startDate);

    @Procedure("SICK_LEAVE_DELETE")
    void deleteSickLeave(Integer employeeNo, LocalDate startDate);
}
