package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Currency {
    int id;

    String name;

    String code;

    String symbol;
}

