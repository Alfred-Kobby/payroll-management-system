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

@Table(name = "Deduction")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@IdClass(DeductionId.class)
public class Deduction {
    @Id
    @Column(name = "employeeNo")
    private Integer employeeNo;

    @Id
    @Column(name = "deductDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate deductDate;

    @Column(name = "deductAmount", nullable = false)
    private BigDecimal deductAmount;

    @Column(name = "deductTypeNo", nullable = false)
    private Integer deductTypeNo;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeeNo", referencedColumnName = "employeeNo", insertable = false, updatable = false)
    private Employee employee;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "deductTypeNo", referencedColumnName = "deductTypeNo", insertable = false, updatable = false)
    private DeductType deductType;
}
