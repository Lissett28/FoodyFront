package com.seniorproject.foody.controllers;

import com.seniorproject.foody.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/basicauth")
@AllArgsConstructor
public class BasicAuthController {

    private AppUserService appUserService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public Map<String, Principal> login(@RequestHeader("Authorization") Principal user){

        return Collections.singletonMap("displayName", user );
    }

}
