package com.example.UserService.service;

import com.example.UserService.dto.AssignRolesToUserDto;
import com.example.UserService.dto.UserResponseDto;
import com.example.UserService.exception.UserNotFoundException;
import com.example.UserService.model.Role;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public UserResponseDto findUserById(UUID id) throws UserNotFoundException;
    public UserResponseDto assignRoleToUser(UUID id, AssignRolesToUserDto requestto) throws UserNotFoundException;
    public List<UserResponseDto> findAllUsers();
}
