package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_requirement")
public class JobRequirement {
    @Id
    @Column(name = "JobRequirementId")
    @JsonProperty("JobRequirementId")
    private Long jobRequirementId;

    @Column(name = "RequiredShortDesc")
    @JsonProperty("RequiredShortDesc")
    private String requiredShortDesc;

    @Column(name = "CompleteDescription")
    @JsonProperty("CompleteDescription")
    private String completeDescription;

    @Column(name = "JobTypeId")
    @JsonProperty("JobTypeId")
    private Long jobTypeId;

    @Column(name = "ClientId")
    @JsonProperty("ClientId")
    private Long clientId;

    @Column(name = "AgentId")
    @JsonProperty("AgentId")
    private Long agentId;

    @Column(name = "PartnerId")
    @JsonProperty("PartnerId")
    private Long partnerId;

    @Column(name = "ShiftId")
    @JsonProperty("ShiftId")
    private Integer shiftId;

    @Column(name = "IsHRAAllowance")
    @JsonProperty("IsHRAAllowance")
    private Boolean isHRAAllowance;

    @Column(name = "HRAAllowanceAmount")
    @JsonProperty("HRAAllowanceAmount")
    private Long hraAllowanceAmount;

    @Column(name = "IsTravelAllowance")
    @JsonProperty("IsTravelAllowance")
    private Boolean isTravelAllowance;

    @Column(name = "TravelAllowanceAmount")
    @JsonProperty("TravelAllowanceAmount")
    private Long travelAllowanceAmount;

    @Column(name = "IsFoodAllowance")
    @JsonProperty("IsFoodAllowance")
    private Boolean isFoodAllowance;

    @Column(name = "FoodAllowanceAmount")
    @JsonProperty("FoodAllowanceAmount")
    private Long foodAllowanceAmount;

    @Column(name = "IsForeignReturnCompulsory")
    @JsonProperty("IsForeignReturnCompulsory")
    private Boolean isForeignReturnCompulsory;

    @Column(name = "MinimunDaysRequired")
    @JsonProperty("MinimunDaysRequired")
    private Integer minimunDaysRequired;

    @Column(name = "CertificateRequiredId")
    @JsonProperty("CertificateRequiredId")
    private Integer certificateRequiredId;

    @Column(name = "MinimunCTC")
    @JsonProperty("MinimunCTC")
    private Long minimunCTC;

    @Column(name = "MaximunCTC")
    @JsonProperty("MaximunCTC")
    private Long maximunCTC;

    @Column(name = "IsOTIncludex")
    @JsonProperty("IsOTIncludex")
    private Boolean isOTIncludex;

    @Column(name = "MaxOTHours")
    @JsonProperty("MaxOTHours")
    private Integer maxOTHours;

    @Column(name = "Bonus")
    @JsonProperty("Bonus")
    private Long bonus;

    @Column(name = "CountryId")
    @JsonProperty("CountryId")
    private Integer countryId;

    @Column(name = "MinAgeLimit")
    @JsonProperty("MinAgeLimit")
    private String minAgeLimit;

    @Column(name = "MaxAgeLimit")
    @JsonProperty("MaxAgeLimit")
    private String maxAgeLimit;

    @Column(name = "NoOfPosts")
    @JsonProperty("NoOfPosts")
    private String noOfPosts;

    @Column(name = "SalaryCurrency")
    @JsonProperty("SalaryCurrency")
    private String salaryCurrency;

    @Column(name = "ContractPeriodInMonths")
    @JsonProperty("ContractPeriodInMonths")
    private Integer contractPeriodInMonths;

    @Column(name = "CreatedBy")
    @JsonProperty("CreatedBy")
    private Long createdBy;

    @Column(name = "UpdatedBy")
    @JsonProperty("UpdatedBy")
    private Long updatedBy;

    @Column(name = "CreatedOn")
    @JsonProperty("CreatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdOn;
    @Column(name = "UpdatedOn")
    @JsonProperty("UpdatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedOn;
}
