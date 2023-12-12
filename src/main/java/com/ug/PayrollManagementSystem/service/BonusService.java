package com.ug.PayrollManagementSystem.service;


import com.ug.PayrollManagementSystem.entity.*;
import com.ug.PayrollManagementSystem.model.BonusRequest;
import com.ug.PayrollManagementSystem.model.PayDetailRequest;
import com.ug.PayrollManagementSystem.repository.BonusRepository;
import com.ug.PayrollManagementSystem.repository.BonusTypeRepository;
import com.ug.PayrollManagementSystem.repository.PayDetailsRepository;
import com.ug.PayrollManagementSystem.repository.PayTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class BonusService {

    @Autowired
    BonusRepository bonusRepository;
    @Autowired
    BonusTypeRepository bonusTypeRepository;

    public List<Bonus> getAllBonuses(Integer employeeNo){
        List<Bonus> bonuses =  bonusRepository.findByEmployeeNo(employeeNo);
        log.info("bonuses: {}", bonuses);
        return bonuses;
    }

    public Bonus findBonus(Integer employeeNo, LocalDate bonusDate){
        Bonus bonus = bonusRepository.findByEmployeeNoAndBonusDate(employeeNo, bonusDate);
        log.info("bonus: {}", bonus);
        return bonus;
    }

    public Bonus saveBonus(Bonus bonus){
        return bonusRepository.save(bonus);
    }

    public void updateBonus(BonusRequest bonusRequest, Integer employeeNo, String oldDate){
        LocalDate bonusDate = LocalDate.parse(bonusRequest.getBonusDate());
        LocalDate oldBonusDate = LocalDate.parse(oldDate);
         bonusRepository.updateBonus(employeeNo,bonusDate,new BigDecimal(bonusRequest.getBonusAmount()), bonusRequest.getBonusTypeNo(), oldBonusDate);
    }

    public List<BonusType> getAllBonusType(){
        List<BonusType> bonusTypes = bonusTypeRepository.findAll();
        log.info("bonusTypes: {}", bonusTypes);
        return bonusTypes;
    }

}
