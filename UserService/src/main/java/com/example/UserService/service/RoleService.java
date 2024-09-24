package com.example.UserService.service;

import com.example.UserService.dto.CreateRoleRequestDto;
import com.example.UserService.dto.RoleResponseDto;

import java.util.List;

public interface RoleService {
    public RoleResponseDto createRole(CreateRoleRequestDto requestDto);
    public List<RoleResponseDto> getAllRoles();
}
