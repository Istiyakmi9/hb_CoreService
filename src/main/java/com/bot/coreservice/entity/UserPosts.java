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
        @Column(name = "userPostId")
        @JsonProperty("userPostId")
        Long userPostId;

        @JsonProperty("totalRecords")
        @Transient
        Integer totalRecords;

        @Column(name = "shortDescription")
        @JsonProperty("shortDescription")
        String shortDescription;

        @Column(name = "completeDescription")
        @JsonProperty("completeDescription")
        String completeDescription;

        @Column(name = "fileDetail")
        @JsonProperty("fileDetail")
        String fileDetail;

        @Column(name = "categoryTypeId")
        @JsonProperty("categoryTypeId")
        long categoryTypeId;

        @Column(name = "countryId")
        @JsonProperty("countryId")
        Integer countryId;

        @Column(name = "jobCategoryId")
        @JsonProperty("jobCategoryId")
        Integer jobCategoryId;

        @Column(name = "jobRequirementId")
        @JsonProperty("jobRequirementId")
        long jobRequirementId;

        @Column(name = "postedBy")
        @JsonProperty("postedBy")
        Long postedBy;

        @Column(name = "postedOn")
        @JsonProperty("postedOn")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        Date postedOn;

        @Column(name = "updatedOn")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        Date updatedOn;

        @JsonProperty("fullName")
        @Column(name = "fullName")
        String fullName;

        @Transient
        @JsonProperty("isLiked")
        boolean isLiked;

        @Transient
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @JsonProperty("appliedOn")
        Date appliedOn;

        @JsonProperty("files")
        transient List<FileDetail> files;
}