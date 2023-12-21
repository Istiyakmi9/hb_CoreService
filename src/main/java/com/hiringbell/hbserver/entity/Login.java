package com.hiringbell.hbserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="login")
public class Login {

    @Id
    @Column(name = "LoginId")
    Long loginId;

    @Column(name = "EmployeeId")
    Long employeeId;

    @Column(name = "Email")
    String email;

    @Column(name = "Mobile")
    String mobile;

    @Column(name = "Password")
    String password;


    @Column(name = "RoleId")
    int roleId;

    @Column(name = "CreatedBy")
    Long createdBy;

    @Column(name = "UpdatedBy")
    Long updatedBy;

    @Column(name = "CreatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdOn;

    @Column(name = "UpdatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedOn;


}
