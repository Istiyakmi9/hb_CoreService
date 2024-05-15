package com.bot.coreservice.entity;

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
@Table(name = "applied_posts")
public class AppliedPosts {

    @Id
    @Column(name = "AppliedPostsId")
    @JsonProperty("AppliedPostsId")
    Long appliedPostsId;

    @Column(name = "PostId")
    @JsonProperty("PostId")
    Long postId;

    @Column(name = "UserId")
    @JsonProperty("UserId")
    Long userId;

    @Column(name = "AppliedOn")
    @JsonProperty("AppliedOn")
    Date appliedOn;

    @Column(name = "Location")
    @JsonProperty("Location")
    String location;

    @Column(name = "Latitude")
    @JsonProperty("Latitude")
    String latitude;

    @Column(name = "Longitude")
    @JsonProperty("Longitude")
    String longitude;

    @Column(name = "PostUserId")
    @JsonProperty("PostUserId")
    Long postUserId;

    @Column(name = "IsApplied")
    @JsonProperty("IsApplied")
    boolean isApplied;
}
