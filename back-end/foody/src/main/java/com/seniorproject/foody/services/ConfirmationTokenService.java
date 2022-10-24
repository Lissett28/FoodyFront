package com.seniorproject.foody.services;

import com.seniorproject.foody.entities.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken confirmationToken);
    Optional<ConfirmationToken> getToken(String token);
    void setConfirmedAt(String token);
}
