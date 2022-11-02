package com.seniorproject.foody.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.seniorproject.foody.entities.Userprofile;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthenticationResponse {
    private String sessionId;
    private String firstName;
    private String lastNameInit;
    private Date memberSince;
    private String displayName;
    // locations
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
