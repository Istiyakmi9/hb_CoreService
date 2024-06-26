package com.bot.coreservice.entity;

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
@Table(name="user")
public class User {

        @Id
        @Column(name = "userId")
        @JsonProperty("userId")
        Long userId;

        @Column(name = "firstName")
        @JsonProperty("firstName")
        String firstName;

        @Column(name = "lastName")
        @JsonProperty("lastName")
        String lastName;

        @Column(name = "fatherName")
        @JsonProperty("fatherName")
        String fatherName;

        @Column(name = "motherName")
        @JsonProperty("motherName")
        String motherName;

        @Column(name = "email")
        @JsonProperty("email")
        String email;

        @Column(name = "mobile")
        @JsonProperty("mobile")
        String mobile;

        @Column(name = "alternateNumber")
        @JsonProperty("alternateNumber")
        String alternateNumber;

        @Column(name = "address")
        @JsonProperty("address")
        String address;

        @Column(name = "city")
        @JsonProperty("city")
        String city;

        @Column(name = "state")
        @JsonProperty("state")
        String state;

        @Column(name = "country")
        @JsonProperty("country")
        String country;

        @Column(name = "roleId")
        @JsonProperty("roleId")
        int roleId;

        @Column(name = "jobCategoryId")
        @JsonProperty("jobCategoryId")
        int jobCategoryId;

        @Column(name = "categoryTypeIds")
        @JsonProperty("categoryTypeIds")
        String categoryTypeIds;

        @Column(name = "jobLocationIds")
        @JsonProperty("jobLocationIds")
        String jobLocationIds;

        @Column(name = "designationId")
        @JsonProperty("designationId")
        int designationId;

        @Column(name = "pinCode")
        @JsonProperty("pinCode")
        int pinCode;

        @Column(name = "reporteeId")
        @JsonProperty("reporteeId")
        long reporteeId;

        @Column(name = "isActive")
        @JsonProperty("isActive")
        boolean isActive;

        @Column(name = "followers")
        @JsonProperty("followers")
        String followers;

        @Column(name = "friends")
        @JsonProperty("friends")
        String friends;

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

        @Column(name = "imageURL")
        @JsonProperty("imageURL")
        String imageURL;

        @Column(nullable = true)
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date dateOfBirth;

        String gender = "m";

        @Column(nullable = true)
        boolean maritalStatus;

        @Column(nullable = true)
        int religionId;

        @Column(nullable = true)
        String nationality;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @Column(nullable = true)
        Date availabilityDate;

        @Column(nullable = true)
        int educationId;

        @Column(nullable = true)
        boolean currentEmploymentStatus;

        int localExperience = 0;

        int overseasExperience = 0;

        @Transient
        String password = "";

        @Transient
        List<UserDocument> userDocs;

        @Transient
        Long[] deletedDocsId;
}
