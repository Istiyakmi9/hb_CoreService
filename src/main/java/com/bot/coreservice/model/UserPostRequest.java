package com.bot.coreservice.model;

import com.bot.coreservice.entity.JobRequirement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserPostRequest extends JobRequirement {
    @JsonProperty("UserPostId")
    Long userPostId;

    @JsonProperty("ShortDescription")
    String shortDescription;

    @JsonProperty("CompleteDescription")
    String completeDescription;

    @JsonProperty("CatagoryTypeId")
    long catagoryTypeId;

    @JsonProperty("PostedBy")
    Long postedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date postedOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedOn;

    @JsonProperty("FileDetail")
    String fileDetail;
}

