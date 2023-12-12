package com.ug.PayrollManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ug.PayrollManagementSystem.model.EmployeeRequest;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Table(name = "Employee")
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "employeeNo")
    private Integer employeeNo;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Column(name = "phoneNumber",nullable = false)
    private String phoneNumber;

    @Column(name = "department",nullable = false)
    private String department;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "employmentDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate employmentDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime created;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updated;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Bonus> bonuses;


    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Deduction> deductions;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Holiday> holidays;

    @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private PayDetails payDetails;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<PayHistory> payHistories;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<SickLeave> sickLeaves;

    public Employee(EmployeeRequest employeeRequest, LocalDate employmentDate){
        this.employeeNo = employeeRequest.getEmployeeNo();
        this.firstName = employeeRequest.getFirstName();
        this.lastName = employeeRequest.getLastName();
        this.phoneNumber = employeeRequest.getPhoneNumber();
        this.department = employeeRequest.getDepartment();
        this.position = employeeRequest.getPosition();
        this.salary = employeeRequest.getSalary();
        this.employmentDate = employmentDate;
        this.created =LocalDateTime.now();
        this.updated =LocalDateTime.now();
    }
}
