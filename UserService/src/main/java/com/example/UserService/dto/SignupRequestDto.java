package com.example.UserService.dto;

import com.example.UserService.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto{
    private String userName;
    private String password;
    private String email;

    public static User from(SignupRequestDto signupRequestDto){
        User user = new User();
        user.setUsername(signupRequestDto.getUserName());
        user.setPassword(signupRequestDto.getPassword());
        user.setEmail(signupRequestDto.getEmail());
        return user;
    }
}
