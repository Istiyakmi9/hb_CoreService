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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resume_detail")
public class ResumeDetail {

    @Column(name = "ResumeId")
    @JsonProperty("ResumeId")
    @Id
    Long resumeId;

    @Column(name = "ResumeDetail")
    @JsonProperty("ResumeDetail")
    String resumeDetail;

    @Column(name = "CreatedBy")
    @JsonProperty("CreatedBy")
    Long createdBy;

    @Column(name = "UpdatedBy")
    @JsonProperty("UpdatedBy")
    Long updatedBy;

    @Column(name = "CreatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("CreatedOn")
    Date createdOn;

    @Column(name = "UpdatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("UpdatedOn")
    Date updatedOn;

}
