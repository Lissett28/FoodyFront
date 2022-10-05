package com.seniorproject.foody.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    String alias;
    String title;

    public Category(String alias, String title) {
        this.alias = alias;
        this.title = title;
    }

    public Category() {
    }
}
