package com.bot.coreservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @Column(name = "BankAccountId")
    @JsonProperty("BankAccountId")
    private int bankAccountId;

    @Column(name = "BankName")
    @JsonProperty("BankName")
    private String bankName;

    @Column(name = "Branch")
    @JsonProperty("Branch")
    private String branch;

    @Column(name = "IFSC")
    @JsonProperty("IFSC")
    private String ifsc;

    @Column(name = "AccountNo")
    @JsonProperty("AccountNo")
    private String accountNo;

    @Column(name = "CreatedBy")
    @JsonProperty("CreatedBy")
    private Long createdBy;

    @Column(name = "UpdatedBy")
    @JsonProperty("UpdatedBy")
    private Long updatedBy;

    @Column(name = "CreatedOn")
    @JsonProperty("CreatedOn")
    private Date createdOn;

    @Column(name = "UpdatedOn")
    @JsonProperty("UpdatedOn")
    private Date updatedOn;
}
