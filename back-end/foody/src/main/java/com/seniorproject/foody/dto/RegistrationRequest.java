package com.seniorproject.foody.dto;

import lombok.*;
import org.springframework.stereotype.Service;

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

}
