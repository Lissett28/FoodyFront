package com.seniorproject.foody.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    String type;

    public Transaction(String type) {
        this.type = type;
    }

    public Transaction() {
    }
}
