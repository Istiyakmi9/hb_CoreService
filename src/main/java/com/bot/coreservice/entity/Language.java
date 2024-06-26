package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "language")
public class Language {
    @Id
    @JsonProperty("languageId")
    @Column(name = "languageId")
    int languageId;

    @JsonProperty("languageName")
    @Column(name = "languageName")
    String languageName;
}
