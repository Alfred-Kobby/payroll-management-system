package com.ug.PayrollManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SickLeaveRequest {
    private Integer employeeNo;
    private String startDate;
    private String endDate;
    private String reason;

}
