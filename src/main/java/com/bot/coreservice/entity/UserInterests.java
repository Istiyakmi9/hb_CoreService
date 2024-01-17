package com.bot.coreservice.entity;

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
    long userInterestId;

   @Column(name = "UserId")
    long userId;

    @Column(name = "InerestId")
   int inerestId;

    public UserInterests(long userId, int inerestId) {
        this.userId = userId;
        this.inerestId = inerestId;
    }
}
