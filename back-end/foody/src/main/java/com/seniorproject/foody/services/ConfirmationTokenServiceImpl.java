package com.seniorproject.foody.services;

import com.seniorproject.foody.dao.ConfirmationTokenRepository;
import com.seniorproject.foody.entities.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return this.confirmationTokenRepository.findByToken(token);
    }

    @Override
    public void setConfirmedAt(String token){
        confirmationTokenRepository.findByToken(token).get().setConfirmedAt(LocalDateTime.now());
    }
}
