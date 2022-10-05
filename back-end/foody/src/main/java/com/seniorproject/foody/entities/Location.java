package com.seniorproject.foody.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    String address1;
    String address2;
    String address3;

    String city;
    String zip_code;
    String country;
    String state;
    List<String> display_address;

    public Location(String address1, String address2, String address3, String city, String zip_code, String country, String state, List<String> display_address) {
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.zip_code = zip_code;
        this.country = country;
        this.state = state;
        this.display_address = display_address;
    }

    public Location() {
    }
}
