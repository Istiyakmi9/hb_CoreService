package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "country")
public class Country {
    @Id
    private int id;

    @JsonProperty("iSO")
    private String iso;

    private String name;

    private String niceName;

    @JsonProperty("iso3")
    private String iso3;

    private short numCode;

    private String phoneCode;
}
