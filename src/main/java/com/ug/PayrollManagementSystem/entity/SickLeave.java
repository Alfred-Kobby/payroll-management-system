package com.ug.PayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "SickLeave")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@IdClass(SickLeaveId.class)
public class SickLeave {
    @Id
    @Column(name = "employeeNo")
    private Integer employeeNo;

    @Id
    @Column(name = "startDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "reason", nullable = false)
    private String reason;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeeNo", referencedColumnName = "employeeNo", insertable = false, updatable = false)
    private Employee employee;

    public SickLeave(Integer employeeNo, LocalDate startDate, LocalDate endDate, String reason){
        this.employeeNo = employeeNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }
}
