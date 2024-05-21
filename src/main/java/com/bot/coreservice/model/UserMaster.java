package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMaster {


    Long userId;

    String firstName;

    String lastName;

    String fatherName;

    String motherName;

    String email;

    String mobile;

    String alternateNumber;

    String address;

    String city;

    String state;

    String country;

    int roleId;

    int designationId;

    int pinCode;

    long reporteeId;

    @JsonProperty("isActive")
    boolean isActive;

    int jobCategoryId;

    List<Integer> categoryTypeIds;

    List<Integer> jobLocationIds;

//employee_detail fields

    String pan;

    String aadhar;

    String passportNumber;

    String bankName;

    String branch;

    String ifscCode;

    int jobTypeId;

    int experienceInMonths;

    String LastCompanyName;



    String designation;

    BigDecimal salary;

    BigDecimal expectedSalary;

    String expectedDesignation;

    String accountNo;

//    employee_medical_detail fields

    long userMedicalDetailId;

    long medicalConsultancyId;

    String ConsultedBy;



    long referenceId;

    long reportId;

    String ReportPath;

}
