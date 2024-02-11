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
@Table(name = "liked_posts")
public class LikedPosts {

    @Id
    @Column(name = "LikedPostsId")
    @JsonProperty("LikedPostsId")
    Long likedPostsId;

    @Column(name = "PostId")
    @JsonProperty("PostId")
    Long postId;

    @Column(name = "UserId")
    @JsonProperty("UserId")
    Long userId;

    @Column(name = "LikedOn")
    @JsonProperty("LikedOn")
    Date likedOn;

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

    @Column(name = "IsLiked")
    @JsonProperty("IsLiked")
    boolean isLiked;
}
