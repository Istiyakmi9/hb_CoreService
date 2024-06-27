package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "education")
public class Education {
    @Id
    @JsonProperty("educationId")
    @Column(name = "educationId")
    int educationId;

    @JsonProperty("educationName")
    @Column(name = "educationName")
    String educationName;
}
