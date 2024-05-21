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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="login")
public class Login {
    @Id
    @Column(name = "loginId")
    @JsonProperty("loginId")
    Long loginId;

    @Column(name = "userId")
    @JsonProperty("userId")
    Long userId;

    @Column(name = "email")
    @JsonProperty("email")
    String email;

    @Column(name = "mobile")
    @JsonProperty("mobile")
    String mobile;

    @Column(name = "password")
    @JsonProperty("password")
    String password;

    @Column(name = "roleId")
    @JsonProperty("roleId")
    int roleId;

    @Column(name = "isActive")
    @JsonProperty("isActive")
    boolean isActive;

    @Column(name = "createdBy")
    @JsonProperty("createdBy")
    Long createdBy;

    @Column(name = "updatedBy")
    @JsonProperty("updatedBy")
    Long updatedBy;

    @Column(name = "createdOn")
    @JsonProperty("createdOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdOn;

    @Column(name = "updatedOn")
    @JsonProperty("updatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedOn;

    @Column(name = "isAccountConfig")
    @JsonProperty("isAccountConfig")
    boolean isAccountConfig;
}
