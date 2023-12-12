package com.ug.PayrollManagementSystem.service;


import com.ug.PayrollManagementSystem.entity.Bonus;
import com.ug.PayrollManagementSystem.entity.BonusType;
import com.ug.PayrollManagementSystem.entity.SickLeave;
import com.ug.PayrollManagementSystem.model.BonusRequest;
import com.ug.PayrollManagementSystem.repository.BonusRepository;
import com.ug.PayrollManagementSystem.repository.BonusTypeRepository;
import com.ug.PayrollManagementSystem.repository.SickLeaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class SickLeaveService {

    @Autowired
    SickLeaveRepository sickLeaveRepository;


    public List<SickLeave> getAllSickLeaves(Integer employeeNo){
        List<SickLeave> sickLeaves =  sickLeaveRepository.findByEmployeeNo(employeeNo);
        log.info("sickLeaves: {}", sickLeaves);
        return sickLeaves;
    }

    public SickLeave findSickLeave(Integer employeeNo, LocalDate bonusDate){
        SickLeave sickLeave = sickLeaveRepository.findByEmployeeNoAndStartDate(employeeNo, bonusDate);
        log.info("sickLeave: {}", sickLeave);
        return sickLeave;
    }

    public SickLeave saveSickLeave(SickLeave sickLeave){
        return sickLeaveRepository.save(sickLeave);
    }

    public void deleteSickLeave(Integer employeeNo, LocalDate startDate){
        sickLeaveRepository.deleteSickLeave(employeeNo, startDate);
    }

//    public void updateBonus(BonusRequest bonusRequest, Integer employeeNo, String oldDate){
//        LocalDate bonusDate = LocalDate.parse(bonusRequest.getBonusDate());
//        LocalDate oldBonusDate = LocalDate.parse(oldDate);
//         bonusRepository.updateBonus(employeeNo,bonusDate,new BigDecimal(bonusRequest.getBonusAmount()), bonusRequest.getBonusTypeNo(), oldBonusDate);
//    }


}
