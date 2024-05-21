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

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_medical_detail")
public class UserMedicalDetail {

    @Id
    @Column(name = "userMedicalDetailId")
    @JsonProperty("userMedicalDetailId")
    long userMedicalDetailId;

    @Column(name = "userId")
    @JsonProperty("userId")
    long userId;

    @Column(name = "medicalConsultancyId")
    @JsonProperty("medicalConsultancyId")
    long medicalConsultancyId;

    @Column (name = "consultedBy")
    @JsonProperty("consultedBy")
    String ConsultedBy;

    @Column(name = "consultedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("consultedOn")
    Date consultedOn;

    @Column(name = "referenceId")
    @JsonProperty("referenceId")
    long referenceId;

    @Column(name = "reportId")
    @JsonProperty("reportId")
    long reportId;

    @Column(name = "reportPath")
    @JsonProperty("reportPath")
    String ReportPath;

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
