package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
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

    String categoryTypeIds;
    List<Integer> categoryTypeIdList;

    String jobLocationIds;
    List<Integer> jobLocationIdList;

    String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date dateOfBirth;
    String gender = "m";
    boolean maritalStatus;
    int religionId;
    String nationality;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date availabilityDate;
    int educationId;
    boolean currentEmploymentStatus;
    int localExperience = 0;
    int overseasExperience = 0;

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
    @JsonProperty("imageURL")
    String imageURL;

}
