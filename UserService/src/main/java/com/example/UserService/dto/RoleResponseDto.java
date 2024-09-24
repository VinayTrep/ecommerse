package com.example.UserService.dto;

import com.example.UserService.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
    private String roleId;
    private String role;

    public static RoleResponseDto from(Role role) {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setRole(role.getRoleName());
        roleResponseDto.setRoleId(role.getId().toString());
        return roleResponseDto;
    }
}
