package com.ug.PayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "DeductType")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DeductType {

    @Id
    @Column(name = "deductTypeNo")
    private Integer deductTypeNo;

    @Column(name = "deductDescription")
    private Integer deductDescription;

    @OneToOne(mappedBy = "deductType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Deduction deduction;


}
