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
    @Column(name = "UserInterestId")
    @JsonProperty("UserInterestId")
    long userInterestId;

   @Column(name = "UserId")
   @JsonProperty("UserId")
   long userId;

    @Column(name = "InerestId")
    @JsonProperty("InerestId")
    int inerestId;

    public UserInterests(long userId, int inerestId) {
        this.userId = userId;
        this.inerestId = inerestId;
    }
}
