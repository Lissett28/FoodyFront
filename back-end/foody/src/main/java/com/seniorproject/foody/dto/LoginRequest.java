package com.seniorproject.foody.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
public class LoginRequest {
    String username;
    String password;
}
