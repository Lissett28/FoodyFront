package com.seniorproject.foody.controllers;

import com.seniorproject.foody.dto.RegistrationRequest;
import com.seniorproject.foody.services.RegistrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {

    private RegistrationServiceImpl registrationService;

    @PostMapping()
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
