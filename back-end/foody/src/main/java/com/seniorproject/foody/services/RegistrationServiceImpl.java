package com.seniorproject.foody.services;

import com.seniorproject.foody.dto.RegistrationRequest;
import com.seniorproject.foody.entities.AppUser;
import com.seniorproject.foody.entities.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean validEmail = emailValidator.test(request.getEmail());
        if(!validEmail){
            throw new IllegalStateException("email not valid");
        }

        return appUserService.signUpUser(
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
    }
}
