package com.example.UserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String sessionId;
    private String token;

    public LoginResponseDto(String sessionId, String token) {
        this.sessionId = sessionId;
        this.token = token;
    }

    public LoginResponseDto() {
    }
}
