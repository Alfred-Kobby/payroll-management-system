package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.PayDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDate;
import java.util.List;

public interface PayDetailsRepository extends JpaRepository<PayDetails, Integer> {
    List<PayDetails> findByEmployeeNo(Integer employeeNo);

    @Procedure("PAY_DETAILS_INSERT")
    void saveNewPayDetails(Integer employeeNo, LocalDate startDate, Integer routingNumber, String accountType, String bankName, String bankAddress, Integer payTypeNo);
}
