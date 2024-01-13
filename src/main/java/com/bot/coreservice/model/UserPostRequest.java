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
    @JsonProperty("UserPostId")
    Long userPostId;

    @JsonProperty("ShortDescription")
    String shortDescription;

    @JsonProperty("CatagoryTypeId")
    long catagoryTypeId;

    @JsonProperty("PostedBy")
    Long postedBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("PostedOn")
    Date postedOn;

    @JsonProperty("FileDetail")
    String fileDetail;

    @JsonProperty("Files")
    List<FileDetail> files;
}

