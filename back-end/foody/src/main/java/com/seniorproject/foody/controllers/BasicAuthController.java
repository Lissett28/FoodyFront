package com.seniorproject.foody.controllers;

import com.seniorproject.foody.config.PasswordEncoder;
import com.seniorproject.foody.dao.AppUserRepository;
import com.seniorproject.foody.dto.AuthenticationBean;
import com.seniorproject.foody.dto.LoginRequest;
import com.seniorproject.foody.entities.AppUser;
import com.seniorproject.foody.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/basicauth")
public class BasicAuthController {


    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @GetMapping(path = "/auth")
    public String basicauth(@Validated @RequestBody LoginRequest loginRequest) {
        boolean valid = false;
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(loginRequest.getPassword());
        List<AppUser> list = appUserRepository.findAll();
        return list.get(0).getDisplayName();

    }
}
