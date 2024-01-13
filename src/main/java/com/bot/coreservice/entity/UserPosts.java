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
@Table(name="userposts")
public class UserPosts {

        @Id
        @Column(name = "UserPostId")
        @JsonProperty("UserPostId")
        Long userPostId;

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
        @Transient
        String fullName;

        @JsonProperty("Files")
        private transient List<FileDetail> files;
}
