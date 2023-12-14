package com.epfo.springsecurity.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JWTAuthenticationResponse {
    private String token;
    private String refreshToken;
}
