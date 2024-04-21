package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {
    @JsonProperty("Id")
    int id;

    @JsonProperty("Name")
    String name;

    @JsonProperty("Code")
    String code;

    @JsonProperty("Symbol")
    String symbol;
}

