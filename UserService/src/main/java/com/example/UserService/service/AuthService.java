package com.example.UserService.service;

import com.example.UserService.dto.*;
import com.example.UserService.exception.InvalidTokenException;
import com.example.UserService.exception.UserNotFoundException;
import com.example.UserService.model.SessionType;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) throws UserNotFoundException;
    public UserResponseDto signup(SignupRequestDto signupRequestDto);
    public SessionType validateSession(ValidateRequestDto requestDto) throws InvalidTokenException;
    public LogoutResponseDto logout(LogoutRequestDto logoutRequestDto);
}
