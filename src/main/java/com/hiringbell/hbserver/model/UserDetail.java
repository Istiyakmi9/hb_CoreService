package com.hiringbell.hbserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    @JsonProperty("Token")
    String token;

    @JsonProperty("TokenExpiryDuration")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date tokenExpiryDuration;

    @JsonProperty("EmployeeId")
    Long employeeId;

    @JsonProperty("Email")
    String email;

    @JsonProperty("Mobile")
    String mobile;

    @JsonProperty("RoleId")
    int roleId;
}
