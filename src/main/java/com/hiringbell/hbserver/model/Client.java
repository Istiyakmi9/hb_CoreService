package com.hiringbell.hbserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "Id")
    @JsonProperty("Id")
    private int id;

    @Column(name = "RepresenterId")
    @JsonProperty("RepresenterId")
    private int representerId;

    @Column(name = "CompanyName")
    @JsonProperty("CompanyName")
    private String companyName;

    @Column(name = "Description")
    @JsonProperty("Description")
    private String description;

    @Column(name = "ClientType")
    @JsonProperty("ClientType")
    private int clientType;

    @Column(name = "Address")
    @JsonProperty("Address")
    private String address;

    @Column(name = "Email")
    @JsonProperty("Email")
    private String email;

    @Column(name = "AlternateEmail_1")
    @JsonProperty("AlternateEmail_1")
    private String alternateEmail1;

    @Column(name = "AlternateEmail_2")
    @JsonProperty("AlternateEmail_2")
    private String alternateEmail2;

    @Column(name = "Modile")
    @JsonProperty("Modile")
    private String mobile;

    @Column(name = "AlternetModile_1")
    @JsonProperty("AlternetModile_1")
    private String alternateMobile1;

    @Column(name = "AlternetModile_2")
    @JsonProperty("AlternetModile_2")
    private String alternateMobile2;

    @Column(name = "GSTIN")
    @JsonProperty("GSTIN")
    private String gstin;

    @Column(name = "LicenseNo")
    @JsonProperty("LicenseNo")
    private String licenseNo;

    @Column(name = "PANNum")
    @JsonProperty("PANNum")
    private String panNumb;

    @Column(name = "LegalEntity")
    @JsonProperty("LegalEntity")
    private String legalEntity;

    @Column(name = "Website")
    @JsonProperty("Website")
    private String website;

    @Column(name = "BankDetailId")
    @JsonProperty("BankDetailId")
    private int bankDetailId;

    @Column(name = "CountryCode")
    @JsonProperty("CountryCode")
    private String countryCode;

    @Column(name = "CreatedOn")
    @JsonProperty("CreatedOn")
    private Date createdOn;

    @Column(name = "CreatedBy")
    @JsonProperty("CreatedBy")
    private long createdBy;

    @Column(name = "UpdatedOn")
    @JsonProperty("UpdatedOn")
    private Date updatedOn;

    @Column(name = "UpdatedBy")
    @JsonProperty("UpdatedBy")
    private long updatedBy;
}
