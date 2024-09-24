package com.example.UserService.controller;

import com.example.UserService.dto.*;
import com.example.UserService.model.SessionType;
import com.example.UserService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    //user signup /signup takes SignupRequestDto and returns userResponseDto
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return  ResponseEntity.ok(authService.signup(signupRequestDto));
    }

    // user login /login takes loginRequestDto and returns userResponseDto
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return  authService.login(loginRequestDto);
    }
    // user validate /validate takes
    @PostMapping("/validate")
    public ResponseEntity<SessionType> validate(@RequestBody ValidateRequestDto validateRequestDto){
        return ResponseEntity.ok(authService.validateSession(validateRequestDto));
    }
    // user logout /logout takes session id and logs him out
    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(@RequestBody LogoutRequestDto requestDto){
        return  ResponseEntity.ok(authService.logout(requestDto));
    }
}
