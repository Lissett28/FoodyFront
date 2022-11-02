package com.seniorproject.foody.dto;

import com.seniorproject.foody.entities.Userprofile;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String username;
    private final String password;
    private final String displayName;

    private final Date memberSince;
    private final String street;
    private final String city;
    private final String state;
    private final String zipCode;
    private final String lastNameInit;

}
