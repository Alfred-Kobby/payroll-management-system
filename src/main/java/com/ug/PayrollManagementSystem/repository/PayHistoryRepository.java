package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.PayHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayHistoryRepository extends JpaRepository<PayHistory, Integer> {
    PayHistory findByPayNo(Integer payNo);
}
