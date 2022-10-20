package com.seniorproject.foody.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String sessionId;
    private String displayName;
    private String jwtToken;
}
