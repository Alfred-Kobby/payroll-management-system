package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.BonusType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonusTypeRepository extends JpaRepository<BonusType, Integer> {
    BonusType findByBonusTypeNo(Integer bonusTypeNo);
}
