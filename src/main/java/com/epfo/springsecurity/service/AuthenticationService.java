package com.epfo.springsecurity.service;

import com.epfo.springsecurity.dto.JWTAuthenticationResponse;
import com.epfo.springsecurity.dto.RefreshTokenRequest;
import com.epfo.springsecurity.dto.SignUpRequest;
import com.epfo.springsecurity.dto.SigningRequest;
import com.epfo.springsecurity.entities.User;

public interface AuthenticationService {
    public User signUp(SignUpRequest signUpRequest);
    public JWTAuthenticationResponse signing(SigningRequest signingRequest);
    JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
