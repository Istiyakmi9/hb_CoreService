package com.bot.coreservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
        import lombok.Data;

@Data
@Entity
@Table(name = "religion")
public class Religion {
    @Id
    @JsonProperty("religionId")
    @Column(name = "religionId")
    int religionId;

    @JsonProperty("religionName")
    @Column(name = "religionName")
    String religionName;


}
