package com.seniorproject.foody.controllers;

import com.seniorproject.foody.dto.RegistrationRequest;
import com.seniorproject.foody.services.RegistrationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {

    private RegistrationServiceImpl registrationService;

    @PostMapping()
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
