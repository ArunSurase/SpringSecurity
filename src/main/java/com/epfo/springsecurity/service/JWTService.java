package com.epfo.springsecurity.service;

import com.epfo.springsecurity.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public interface JWTService {

    public String extractUsername(String token);

    public String generateToken(UserDetails userdetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userdetails);
}
