package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.DeductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeductTypeRepository extends JpaRepository<DeductType, Integer> {
    DeductType findByDeductTypeNo(Integer bonusTypeNo);
}
