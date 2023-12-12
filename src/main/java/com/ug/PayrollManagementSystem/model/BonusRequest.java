package com.ug.PayrollManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BonusRequest {
    private Integer employeeNo;
    private String bonusDate;
    private String bonusAmount;
    private Integer bonusTypeNo;

}
