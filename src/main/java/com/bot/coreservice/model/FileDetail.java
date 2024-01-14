package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FileDetail {
    @JsonProperty("FileDetailId")
    private int fileDetailId;

    @JsonProperty("FilePath")
    private String filePath;
}
