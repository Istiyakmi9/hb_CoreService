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

    @Column(name = "resumeId")
    @JsonProperty("resumeId")
    @Id
    Long resumeId;

    @Column(name = "resumeDetail")
    @JsonProperty("resumeDetail")
    String resumeDetail;

    @Column(name = "createdBy")
    @JsonProperty("createdBy")
    Long createdBy;

    @Column(name = "updatedBy")
    @JsonProperty("updatedBy")
    Long updatedBy;

    @Column(name = "createdOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("createdOn")
    Date createdOn;

    @Column(name = "updatedOn")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("updatedOn")
    Date updatedOn;

}
