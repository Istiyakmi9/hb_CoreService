package com.hiringbell.hbserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "client_type")
public class ClientType {
    @Id
    @Column(name = "ClientTypeId")
    @JsonProperty("ClientTypeId")
    private int clientTypeId;

    @Column(name = "ClientType")
    @JsonProperty("ClientType")
    private String clientType;
}
