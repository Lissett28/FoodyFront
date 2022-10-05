package com.seniorproject.foody.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseResult {
    List<Business> businesses;

    public ResponseResult(List<Business> businesses) {
        this.businesses = businesses;
    }

    public ResponseResult() {
    }
}
