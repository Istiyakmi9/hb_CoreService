package com.hiringbell.hbserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMaster {

    Long employeeId;
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
    Date lastWorkingDate = new Date();
    String designation;
    BigDecimal salary;
    BigDecimal expectedSalary;
    String expectedDesignation;
    String accountNo;

    //employee_medical_detail fields
//    long employeeMedicalDetailId;
//    long medicalConsultancyId;
//    String ConsultedBy;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    Date consultedOn;
//    long referenceId;
//    long reportId;
//    String ReportPath;
//    Long createdBy;
//    Long updatedBy;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    Date createdOn;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    Date updatedOn;

}
