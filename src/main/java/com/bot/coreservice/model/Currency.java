package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {
    @JsonProperty("Id")
    int id;

    @JsonProperty("Country")
    String country;

    @JsonProperty("Currency")
    String currency;

    @JsonProperty("Code")
    String code;

    @JsonProperty("MinorUnit")
    String minorUnit;

    @JsonProperty("Symbol")
    String symbol;
}

