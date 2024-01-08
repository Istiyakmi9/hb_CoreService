package com.hiringbell.hbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    long reporteeId;

    //employee_detail fields
    String pan;
    String aadhar;
    String passportNumber;
    String bankName;
    String branch;
    String IfscCode;
    int jobTypeId;
    int experienceInMonths;
    String LastCompanyName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date lastWorkingDate;
    String designation;
    BigDecimal salary;
    BigDecimal expectedSalary;
    String expectedDesignation;

    //employee_medical_detail fields
    long employeeMedicalDetailId;
    long medicalConsultancyId;
    String ConsultedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date consultedOn;
    long referenceId;
    long reportId;
    String ReportPath;
    Long createdBy;
    Long updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedOn;

}
