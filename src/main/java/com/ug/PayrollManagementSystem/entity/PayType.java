package com.ug.PayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "PayType")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PayType {

    @Id
    @Column(name = "payTypeNo")
    private Integer payTypeNo;

    @Column(name = "payTypeDescription")
    private String payTypeDescription;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "payType", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private PayDetails payDetails;
}
