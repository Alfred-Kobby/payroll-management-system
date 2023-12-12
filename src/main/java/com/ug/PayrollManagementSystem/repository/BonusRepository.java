package com.ug.PayrollManagementSystem.repository;

import com.ug.PayrollManagementSystem.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BonusRepository extends JpaRepository<Bonus, Integer> {
    List<Bonus> findByEmployeeNo(Integer employeeNo);

    Bonus findByEmployeeNoAndBonusDate(Integer employeeNo, LocalDate bonusDate);

    @Procedure("BONUS_UPDATE")
    void updateBonus(Integer p_employeeNo, LocalDate p_bonusDate, BigDecimal p_bonusAmount, Integer p_bonusTypeNo, LocalDate p_oldBonusDate);
}
