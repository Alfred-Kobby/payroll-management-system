package com.ug.PayrollManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PayDetailRequest {
    private Integer employeeNo;
    private String startDate;
    private String routingNumber;
    private String accountType;
    private String bankName;
    private String bankAddress;
    private Integer payTypeNo;

}
