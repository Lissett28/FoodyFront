package com.seniorproject.foody.services;

import com.seniorproject.foody.dto.RegistrationRequest;
import com.seniorproject.foody.entities.AppUser;
import com.seniorproject.foody.entities.AppUserRole;
import com.seniorproject.foody.entities.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailServiceImpl emailService;

    public String register(RegistrationRequest request) {
        boolean validEmail = emailValidator.test(request.getEmail());
        if(!validEmail){
            throw new IllegalStateException("email not valid");
        }

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getDisplayName(),
                        request.getEmail(),
                        request.getUsername(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        String email = buildEmail(request.getDisplayName(),link);
        emailService.send(request.getEmail(),email);


        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() ->
                new IllegalStateException("token not found"));
        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email is already confirmed");
        }
        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getUsername()
        );
        return "confirmed";
    }

    private String buildEmail(String name, String link){
        return
                "<p>Hi " + name + "</p>\n" +
                "<p>link: " + link +"</p>\n";
    }
}
