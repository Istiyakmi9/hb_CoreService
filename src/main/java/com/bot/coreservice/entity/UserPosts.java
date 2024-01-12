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

        @Column(name = "CatagoryTypeId")
        @JsonProperty("CatagoryTypeId")
        Long catagoryTypeId;

        @Column(name = "JobRequirementId")
        @JsonProperty("JobRequirementId")
        Long jobRequirementId;

        @Column(name = "PostedBy")
        @JsonProperty("PostedBy")
        Long postedBy;

        @Column(name = "PostedOn")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date postedOn;

        @Column(name = "UpdatedOn")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date updatedOn;

}
