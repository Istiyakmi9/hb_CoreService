package com.hiringbell.hbserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee_detail")
public class EmployeeDetail {

    @Id
    @Column(name = "EmployeeId")
    long employeeId;

    @Column(name = "PAN")
    String pan;

    @Column(name = "Aadhar")
    String aadhar;

    @Column(name = "PassportNumber")
    String passportNumber;

    @Column(name = "BankName")
    String bankName;

    @Column(name = "Branch")
    String branch;

    @Column(name = "IFSCCode")
    String IfscCode;

    @Column(name = "JobTypeId")
    int jobTypeId;


}
