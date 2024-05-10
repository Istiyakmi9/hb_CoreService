package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestModel {
    @JsonProperty("Content")
    String content;
}
