package com.ug.PayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Table(name = "BonusType")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BonusType {

    @Id
    @Column(name = "bonusTypeNo")
    private Integer bonusTypeNo;

    @Column(name = "bonusDescription")
    private String bonusDescription;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "bonusType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Bonus bonus;

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((bonusTypeNo == null) ? 0 : bonusTypeNo.hashCode());
//        result = prime * result + ((bonusDescription == null) ? 0 : bonusDescription.hashCode());
//        // Exclude the related entity from hashCode
//        return result;
//    }
}
