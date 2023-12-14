package com.epfo.springsecurity.service;

import com.epfo.springsecurity.dto.JWTAuthenticationResponse;
import com.epfo.springsecurity.dto.RefreshTokenRequest;
import com.epfo.springsecurity.dto.SignUpRequest;
import com.epfo.springsecurity.dto.SigningRequest;
import com.epfo.springsecurity.entities.Role;
import com.epfo.springsecurity.entities.User;
import com.epfo.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signUp(SignUpRequest signUpRequest){
        User newUser = new User();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setFirstname(signUpRequest.getFirstName());
        newUser.setSecondname(signUpRequest.getLastName());
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(newUser);
    }

    public JWTAuthenticationResponse signing(SigningRequest signingRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signingRequest.getEmail(), signingRequest.getPassword()));
        var user = userRepository.findByEmail(signingRequest.getEmail()).orElseThrow( () -> new IllegalArgumentException("Invalid Email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
