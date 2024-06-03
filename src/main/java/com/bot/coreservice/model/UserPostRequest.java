package com.bot.coreservice.model;

import com.bot.coreservice.entity.JobRequirement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPostRequest extends JobRequirement {
    @JsonProperty("userPostId")
    Long userPostId;

    String fullName;
    String jobTypeName;
    String countryName;
    String currencyName;

    @JsonProperty("jobAppliedOn")
    Date jobAppliedOn;

    @JsonProperty("shortDescription")
    String shortDescription;

    @JsonProperty("categoryTypeId")
    long categoryTypeId;

    @JsonProperty("postedBy")
    Long postedBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("postedOn")
    Date postedOn;

    @JsonProperty("fileDetail")
    String fileDetail;

    @JsonProperty("files")
    List<FileDetail> files;

    @JsonProperty("jobCategoryId")
    Integer jobCategoryId;
}

