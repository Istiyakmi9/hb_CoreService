package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "job_type")
public class JobType {
    @Id
//    @OneToOne(mappedBy = "jobTypeId", cascade = CascadeType.ALL)
    @JsonProperty("jobTypeId")
    @Column(name = "jobTypeId")
    int jobTypeId;

    @JsonProperty("jobTypeName")
    @Column(name = "jobTypeName")
    String jobTypeName;

    @JsonProperty("jobDescription")
    @Column(name = "jobDescription")
    String jobDescription;

    @Column(name = "categoryId")
    @JsonProperty("categoryId")
    int categoryId;

    @Column(name = "createdBy")
    @JsonProperty("createdBy")
    Long createdBy;

    @Column(name = "updatedBy")
    @JsonProperty("updatedBy")
    Long updatedBy;

    @Column(name = "createdOn")
    @JsonProperty("createdOn")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Date createdOn;

    @Column(name = "updatedOn")
    @JsonProperty("updatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    Date updatedOn;
}
