package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_interests")
public class UserInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userInterestId")
    @JsonProperty("userInterestId")
    long userInterestId;

    @Column(name = "userId")
    @JsonProperty("userId")
    long userId;

    @Column(name = "interestId")
    @JsonProperty("interestId")
    int interestId;

    public UserInterests(long userId, int interestId) {
        this.userId = userId;
        this.interestId = interestId;
    }
}
