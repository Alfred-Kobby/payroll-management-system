package com.ug.PayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ug.PayrollManagementSystem.model.BonusRequest;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "Bonus")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@IdClass(BonusId.class)
public class Bonus {
    @Id
    @Column(name = "employeeNo")
    private Integer employeeNo;

    @Id
    @Column(name = "bonusDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate bonusDate;

    @Column(name = "bonusAmount", nullable = false)
    private BigDecimal bonusAmount;

    @Column(name = "bonusTypeNo", nullable = false)
    private Integer bonusTypeNo;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeeNo", referencedColumnName = "employeeNo", insertable = false, updatable = false)
    private Employee employee;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bonusTypeNo", referencedColumnName = "bonusTypeNo", insertable = false, updatable = false)
    private BonusType bonusType;

    public Bonus(BonusRequest bonusRequest, LocalDate bonusDate){
        this.employeeNo = bonusRequest.getEmployeeNo();
        this.bonusDate = bonusDate;
        this.bonusAmount = new BigDecimal(bonusRequest.getBonusAmount());
        this.bonusTypeNo = bonusRequest.getBonusTypeNo();
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((employeeNo == null) ? 0 : employeeNo.hashCode());
//        result = prime * result + ((bonusDate == null) ? 0 : bonusDate.hashCode());
//        result = prime * result + ((bonusAmount == null) ? 0 : bonusAmount.hashCode());
//        result = prime * result + ((bonusTypeNo == null) ? 0 : bonusTypeNo.hashCode());
//        // Exclude the related entity from hashCode
//        return result;
//    }
}
