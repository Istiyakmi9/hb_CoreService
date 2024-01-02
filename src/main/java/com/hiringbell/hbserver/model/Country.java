package com.hiringbell.hbserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "Id")
    @JsonProperty("Id")
    private int id;

    @Column(name = "ISO")
    @JsonProperty("ISO")
    private String iso;

    @Column(name = "Name")
    @JsonProperty("Name")
    private String name;

    @Column(name = "NiceName")
    @JsonProperty("NiceName")
    private String niceName;

    @Column(name = "ISO3")
    @JsonProperty("ISO3")
    private String iso3;

    @Column(name = "NumCode")
    @JsonProperty("NumCode")
    private short numCode;

    @Column(name = "PhoneCode")
    @JsonProperty("PhoneCode")
    private String phoneCode;
}
