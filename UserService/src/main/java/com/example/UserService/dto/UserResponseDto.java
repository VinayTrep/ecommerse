package com.example.UserService.dto;

import com.example.UserService.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private String userId;
    private String userName;
    private String email;
    private List<RoleResponseDto> roles;

    public static UserResponseDto from(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getId().toString());
        userResponseDto.setUserName(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRoles(user.getRoles().stream().map(RoleResponseDto::from).toList());
        return userResponseDto;
    }
}
