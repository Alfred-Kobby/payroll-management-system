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

@Table(name = "PayDetails")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@IdClass(PayDetailsId.class)
public class PayDetails {
    @Id
    @Column(name = "employeeNo")
    private Integer employeeNo;

    @Id
    @Column(name = "startDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @Column(name = "routingNumber", nullable = false)
    private BigDecimal routingNumber;

    @Column(name = "accountType", nullable = false)
    private String accountType;

    @Column(name = "bankName", nullable = false)
    private String bankName;

    @Column(name = "bankAddress", nullable = false)
    private String bankAddress;

    @Column(name = "payTypeNo", nullable = false)
    private String payTypeNo;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeeNo", referencedColumnName = "employeeNo", insertable = false, updatable = false)
    private Employee employee;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "payTypeNo", referencedColumnName = "payTypeNo", insertable = false, updatable = false)
    private PayType payType;
}
