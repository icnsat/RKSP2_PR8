package org.example.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private UserDTO user;
//
//    @Builder.Default
//    private Long expiresIn = 3600L; // 1 hour in seconds
}