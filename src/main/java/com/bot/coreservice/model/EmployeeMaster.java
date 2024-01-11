package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMaster {


    @JsonProperty("EmployeeId")
    Long employeeId;

    @JsonProperty("FirstName")
    String firstName;

    @JsonProperty("LastName")
    String lastName;

    @JsonProperty("FatherName")
    String fatherName;

    @JsonProperty("MotherName")
    String motherName;

    @JsonProperty("Email")
    String email;

    @JsonProperty("Mobile")
    String mobile;

    @JsonProperty("AlternateNumber")
    String alternateNumber;

    @JsonProperty("Address")
    String address;

    @JsonProperty("City")
    String city;

    @JsonProperty("State")
    String state;

    @JsonProperty("Country")
    String country;

    @JsonProperty("RoleId")
    int roleId;

    @JsonProperty("DesignationId")
    int designationId;

    @JsonProperty("PinCode")
    int pinCode;

    @JsonProperty("ReporteeId")
    long reporteeId;

    @JsonProperty("IsActive")
    boolean isActive;

//employee_detail fields

    @JsonProperty("Pan")
    String pan;

    @JsonProperty("Aadhar")
    String aadhar;

    @JsonProperty("PassportNumber")
    String passportNumber;

    @JsonProperty("BankName")
    String bankName;

    @JsonProperty("Branch")
    String branch;

    @JsonProperty("IfscCode")
    String ifscCode;

    @JsonProperty("JobTypeId")
    int jobTypeId;

    @JsonProperty("ExperienceInMonths")
    int experienceInMonths;

    @JsonProperty("LastCompanyName")
    String LastCompanyName;



    @JsonProperty("Designation")
    String designation;

    @JsonProperty("Salary")
    BigDecimal salary;

    @JsonProperty("ExpectedSalary")
    BigDecimal expectedSalary;

    @JsonProperty("ExpectedDesignation")
    String expectedDesignation;

    @JsonProperty("AccountNo")
    String accountNo;

//    employee_medical_detail fields

    @JsonProperty("EmployeeMedicalDetailId")
    long employeeMedicalDetailId;

    @JsonProperty("MedicalConsultancyId")
    long medicalConsultancyId;

    @JsonProperty("ConsultedBy")
    String ConsultedBy;



    @JsonProperty("ReferenceId")
    long referenceId;

    @JsonProperty("ReportId")
    long reportId;

    @JsonProperty("ReportPath")
    String ReportPath;

}
