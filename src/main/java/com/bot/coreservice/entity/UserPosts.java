package com.bot.coreservice.entity;

import com.bot.coreservice.model.FileDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_posts")
public class UserPosts {

        @Id
        @Column(name = "UserPostId")
        @JsonProperty("UserPostId")
        Long userPostId;

        @JsonProperty("TotalRecords")
        @Transient
        Integer totalRecords;

        @Column(name = "ShortDescription")
        @JsonProperty("ShortDescription")
        String shortDescription;

        @Column(name = "CompleteDescription")
        @JsonProperty("CompleteDescription")
        String completeDescription;

        @Column(name = "FileDetail")
        @JsonProperty("FileDetail")
        String fileDetail;


        @Column(name = "CatagoryTypeId")
        @JsonProperty("CatagoryTypeId")
        long catagoryTypeId;

        @Column(name = "CountryId")
        @JsonProperty("CountryId")
        Integer countryId;

        @Column(name = "JobCategoryId")
        @JsonProperty("JobCategoryId")
        Integer jobCategoryId;

        @Column(name = "JobRequirementId")
        @JsonProperty("JobRequirementId")
        long jobRequirementId;

        @Column(name = "PostedBy")
        @JsonProperty("PostedBy")
        Long postedBy;

        @Column(name = "PostedOn")
        @JsonProperty("PostedOn")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        Date postedOn;

        @Column(name = "UpdatedOn")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date updatedOn;

        @JsonProperty("FullName")
        @Column(name = "FullName")
        String fullName;

        @Transient
        @JsonProperty("IsLiked")
        boolean isLiked;

        @JsonProperty("Files")
        transient List<FileDetail> files;
}