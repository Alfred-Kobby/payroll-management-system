package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.PayType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayTypeRepository extends JpaRepository<PayType, Integer> {
    PayType findByPayTypeNo(Integer bonusTypeNo);
}
