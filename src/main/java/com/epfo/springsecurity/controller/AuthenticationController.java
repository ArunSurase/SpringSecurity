package com.epfo.springsecurity.controller;

import com.epfo.springsecurity.dto.JWTAuthenticationResponse;
import com.epfo.springsecurity.dto.RefreshTokenRequest;
import com.epfo.springsecurity.dto.SignUpRequest;
import com.epfo.springsecurity.dto.SigningRequest;
import com.epfo.springsecurity.entities.User;
import com.epfo.springsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signing")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SigningRequest signingRequest){
        return ResponseEntity.ok(authenticationService.signing(signingRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
