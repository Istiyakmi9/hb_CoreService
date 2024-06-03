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
    @Column(name = "jobRequirementId")
    @JsonProperty("jobRequirementId")
    private Long jobRequirementId;

    @Column(name = "requiredShortDesc")
    @JsonProperty("requiredShortDesc")
    private String requiredShortDesc;

    @Column(name = "completeDescription")
    @JsonProperty("completeDescription")
    private String completeDescription;

    @Column(name = "jobTypeId")
//    @OneToOne
//    @JoinColumn(name = "jobTypeId")
    @JsonProperty("jobTypeId")
    private int jobTypeId;

    @Column(name = "clientId")
    @JsonProperty("clientId")
    private Long clientId;

    @Column(name = "agentId")
    @JsonProperty("agentId")
    private Long agentId;

    @Column(name = "partnerId")
    @JsonProperty("partnerId")
    private Long partnerId;

    @Column(name = "shiftId")
    @JsonProperty("shiftId")
    private Integer shiftId;

    @Column(name = "isHRAAllowance")
    @JsonProperty("isHRAAllowance")
    private Boolean isHRAAllowance;

    @Column(name = "hRAAllowanceAmount")
    @JsonProperty("hRAAllowanceAmount")
    private Long hRAAllowanceAmount;

    @Column(name = "isTravelAllowance")
    @JsonProperty("isTravelAllowance")
    private Boolean isTravelAllowance;

    @Column(name = "travelAllowanceAmount")
    @JsonProperty("travelAllowanceAmount")
    private Long travelAllowanceAmount;

    @Column(name = "isFoodAllowance")
    @JsonProperty("isFoodAllowance")
    private Boolean isFoodAllowance;

    @Column(name = "foodAllowanceAmount")
    @JsonProperty("foodAllowanceAmount")
    private Long foodAllowanceAmount;

    @Column(name = "isForeignReturnCompulsory")
    @JsonProperty("isForeignReturnCompulsory")
    private Boolean isForeignReturnCompulsory;

    @Column(name = "minimumDaysRequired")
    @JsonProperty("minimumDaysRequired")
    private Integer minimumDaysRequired;

    @Column(name = "certificateRequiredId")
    @JsonProperty("certificateRequiredId")
    private Integer certificateRequiredId;

    @Column(name = "minimumCTC")
    @JsonProperty("minimumCTC")
    private Long minimumCTC;

    @Column(name = "maximumCTC")
    @JsonProperty("maximumCTC")
    private Long maximumCTC;

    @Column(name = "isOTIncluded")
    @JsonProperty("isOTIncluded")
    private Boolean isOTIncluded;

    @Column(name = "maxOTHours")
    @JsonProperty("maxOTHours")
    private Integer maxOTHours;

    @Column(name = "bonus")
    @JsonProperty("bonus")
    private Long bonus;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private Integer countryId;

    @Column(name = "minAgeLimit")
    @JsonProperty("minAgeLimit")
    private String minAgeLimit;

    @Column(name = "maxAgeLimit")
    @JsonProperty("maxAgeLimit")
    private String maxAgeLimit;

    @Column(name = "noOfPosts")
    @JsonProperty("noOfPosts")
    private String noOfPosts;

    @Column(name = "salaryCurrency")
    @JsonProperty("salaryCurrency")
    private String salaryCurrency;

    @Column(name = "contractPeriodInMonths")
    @JsonProperty("contractPeriodInMonths")
    private Integer contractPeriodInMonths;

    @Column(name = "createdBy")
    @JsonProperty("createdBy")
    private Long createdBy;

    @Column(name = "updatedBy")
    @JsonProperty("updatedBy")
    private Long updatedBy;

    @Column(name = "createdOn")
    @JsonProperty("createdOn")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Date createdOn;

    @Column(name = "updatedOn")
    @JsonProperty("updatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Date updatedOn;

    @Column(name = "dailyWorkingHours")
    @JsonProperty("dailyWorkingHours")
    int dailyWorkingHours;

    @Column(name = "visaType")
    @JsonProperty("visaType")
    int visaType;

    @Column(name = "isMedicalInsuranceProvide")
    @JsonProperty("isMedicalInsuranceProvide")
    boolean isMedicalInsuranceProvide;

    @Column(name = "overseasExperience")
    @JsonProperty("overseasExperience")
    int overseasExperience;

    @Column(name = "localExperience")
    @JsonProperty("localExperience")
    int localExperience;

    @Column(name = "isMon")
    @JsonProperty("isMon")
    Boolean isMon;

    @Column(name = "isTue")
    @JsonProperty("isTue")
    Boolean isTue;

    @Column(name = "isThu")
    @JsonProperty("isThu")
    Boolean isThu;

    @Column(name = "isWed")
    @JsonProperty("isWed")
    Boolean isWed;

    @Column(name = "isFri")
    @JsonProperty("isFri")
    Boolean isFri;

    @Column(name = "isSat")
    @JsonProperty("isSat")
    Boolean isSat;

    @Column(name = "isSun")
    @JsonProperty("isSun")
    Boolean isSun;
}
