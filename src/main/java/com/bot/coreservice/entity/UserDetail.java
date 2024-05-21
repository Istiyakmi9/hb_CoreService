package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_detail")
public class UserDetail {

    @Id
    @Column(name = "userId")
    @JsonProperty("userId")
    long userId;

    @Column(name = "pan")
    @JsonProperty("pan")
    String pan;

    @Column(name = "aadhar")
    @JsonProperty("aadhar")
    String aadhar;

    @Column(name = "passportNumber")
    @JsonProperty("passportNumber")
    String passportNumber;

    @Column(name = "bankName")
    @JsonProperty("bankName")
    String bankName;

    @Column(name = "accountNo")
    @JsonProperty("accountNo")
    String accountNo;

    @Column(name = "branch")
    @JsonProperty("branch")
    String branch;

    @Column(name = "iFSCCode")
    @JsonProperty("iFSCCode")
    String ifscCode;

    @Column(name = "jobTypeId")
    @JsonProperty("jobTypeId")
    int jobTypeId;

    @Column(name = "experienceInMonths")
    @JsonProperty("experienceInMonths")
    int experienceInMonths;

    @Column(name = "lastCompanyName")
    @JsonProperty("lastCompanyName")
    String LastCompanyName;

    @Column(name = "lastWorkingDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastWorkingDate")
    Date lastWorkingDate;

    @Column(name = "designation")
    @JsonProperty("designation")
    String designation;

    @Column(name = "salary")
    @JsonProperty("salary")
    BigDecimal salary;

    @Column(name = "expectedSalary")
    @JsonProperty("expectedSalary")
    BigDecimal expectedSalary;

    @Column(name = "expectedDesignation")
    @JsonProperty("expectedDesignation")
    String expectedDesignation;

    @Column(name = "createdBy")
    @JsonProperty("createdBy")
    Long createdBy;

    @Column(name = "updatedBy")
    @JsonProperty("updatedBy")
    Long updatedBy;

    @Column(name = "createdOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdOn")
    Date createdOn;

    @Column(name = "updatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedOn")
    Date updatedOn;

}
