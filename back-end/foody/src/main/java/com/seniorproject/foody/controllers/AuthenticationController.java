package com.seniorproject.foody.controllers;

import com.seniorproject.foody.dto.AuthenticationResponse;
import com.seniorproject.foody.dto.UsernameAndPasswordAuthenticationRequest;
import com.seniorproject.foody.entities.AppUser;
import com.seniorproject.foody.security.InMemorySessionRegistry;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class AuthenticationController {


    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public InMemorySessionRegistry inMemorySessionRegistry;

    @PostMapping("/v1/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestHeader("Authorization") String request) {
        String[] usernamePassword = new String(Base64.getDecoder().decode(request.getBytes(StandardCharsets.UTF_8))).split(":");
        UsernameAndPasswordAuthenticationRequest user = new UsernameAndPasswordAuthenticationRequest(usernamePassword[0],usernamePassword[1]);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        final String sessionId = inMemorySessionRegistry.registerSession(user.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setSessionId(sessionId);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        authenticationResponse.setDisplayName(appUser.getDisplayName());
        authenticationResponse.setJwtToken(buildJwtToken(authentication));
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/v1/login1")
    public String login1(@RequestHeader("Authorization") String request) {
        String usernamePassword = new String(Base64.getDecoder().decode(request.getBytes(StandardCharsets.UTF_8)));
        return request;
    }

    private String buildJwtToken(Authentication authResult){
        // create a jwt token and send to our client
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
                .signWith(Keys.hmacShaKeyFor("securesecuresecuresecuresecuresecuresecuresecuresecuresecure".getBytes(StandardCharsets.UTF_8))).compact();
        String res = ("Authorization: " + "Bearer " + token);
        return res;
    }


}
