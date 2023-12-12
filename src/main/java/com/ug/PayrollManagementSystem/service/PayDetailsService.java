package com.ug.PayrollManagementSystem.service;


import com.ug.PayrollManagementSystem.entity.PayDetails;
import com.ug.PayrollManagementSystem.entity.PayType;
import com.ug.PayrollManagementSystem.model.PayDetailRequest;
import com.ug.PayrollManagementSystem.repository.PayDetailsRepository;
import com.ug.PayrollManagementSystem.repository.PayTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PayDetailsService {

    @Autowired
    PayDetailsRepository payDetailsRepository;
    @Autowired
    PayTypeRepository payTypeRepository;

    public List<PayDetails> getAllPayDetails(Integer employeeNo){
        List<PayDetails> payDetails =  payDetailsRepository.findByEmployeeNo(employeeNo);
        log.info("payDetails: {}", payDetails);
        return payDetails;
    }

    public void savePayDetails(PayDetailRequest payDetails){
        LocalDate startDate = LocalDate.parse(payDetails.getStartDate());
         payDetailsRepository.saveNewPayDetails(payDetails.getEmployeeNo(),startDate,Integer.parseInt(payDetails.getRoutingNumber()),payDetails.getAccountType(), payDetails.getBankName(), payDetails.getBankAddress(), payDetails.getPayTypeNo());
    }

    public List<PayType> getAllPayType(){
        List<PayType> payTypes = payTypeRepository.findAll();
        log.info("payTypes: {}", payTypes);
        return payTypes;
    }

}
