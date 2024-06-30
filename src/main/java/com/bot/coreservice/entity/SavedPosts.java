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
@Table(name = "saved_posts")
public class SavedPosts {

    @Id
    @Column(name = "savedPostsId")
    @JsonProperty("savedPostsId")
    Long savedPostsId;

    @Column(name = "postId")
    @JsonProperty("postId")
    Long postId;

    @Column(name = "userId")
    @JsonProperty("userId")
    Long userId;

    @Column(name = "savedOn")
    @JsonProperty("savedOn")
    Date savedOn;

    @Column(name = "location")
    @JsonProperty("location")
    String location;

    @Column(name = "latitude")
    @JsonProperty("latitude")
    String latitude;

    @Column(name = "longitude")
    @JsonProperty("longitude")
    String longitude;

    @Column(name = "postUserId")
    @JsonProperty("postUserId")
    Long postUserId;

}
