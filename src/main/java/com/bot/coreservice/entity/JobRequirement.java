package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_requirement")
public class JobRequirement {

    @Id
    @Column(name = "JobRequirementId")
    private Long jobRequirementId;

    @Column(name = "RequiredShortDesc")
    private String requiredShortDesc;

    @Column(name = "CompleteDescription")
    private String completeDescription;

    @Column(name = "JobTypeId")
    private Long jobTypeId;

    @Column(name = "ClientId")
    private Long clientId;

    @Column(name = "AgentId")
    private Long agentId;

    @Column(name = "PartnerId")
    private Long partnerId;

    @Column(name = "ShiftId")
    private Integer shiftId;

    @Column(name = "IsHRAAllowance")
    private Boolean isHRAAllowance;

    @Column(name = "HRAAllowanceAmount")
    private Long hraAllowanceAmount;

    @Column(name = "IsTravelAllowance")
    private Boolean isTravelAllowance;

    @Column(name = "TravelAllowanceAmount")
    private Long travelAllowanceAmount;

    @Column(name = "IsFoodAllowance")
    private Boolean isFoodAllowance;

    @Column(name = "FoodAllowanceAmount")
    private Long foodAllowanceAmount;

    @Column(name = "IsForeignReturnCompulsory")
    private Boolean isForeignReturnCompulsory;

    @Column(name = "MinimunDaysRequired")
    private Integer minimunDaysRequired;

    @Column(name = "CertificateRequiredId")
    private Integer certificateRequiredId;

    @Column(name = "MinimunCTC")
    private Long minimunCTC;

    @Column(name = "MaximunCTC")
    private Long maximunCTC;

    @Column(name = "IsOTIncludex")
    private Boolean isOTIncludex;

    @Column(name = "MaxOTHours")
    private Integer maxOTHours;

    @Column(name = "Bonus")
    private Long bonus;

    @Column(name = "CountryId")
    private Integer countryId;

    @Column(name = "MinAgeLimit")
    private String minAgeLimit;

    @Column(name = "MaxAgeLimit")
    private String maxAgeLimit;

    @Column(name = "NoOfPosts")
    private String noOfPosts;

    @Column(name = "SalaryCurrency")
    private String salaryCurrency;

    @Column(name = "ContractPeriodInMonths")
    private Integer contractPeriodInMonths;

    @Column(name = "CreatedBy")
    private Long createdBy;

    @Column(name = "UpdatedBy")
    private Long updatedBy;

    @Column(name = "CreatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdOn;
    @Column(name = "UpdatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedOn;


}
