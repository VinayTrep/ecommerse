package com.example.UserService.dto;

import com.example.UserService.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleRequestDto {
    private String roleName;

    public static Role from(CreateRoleRequestDto createRoleRequestDto) {
        Role role = new Role();
        role.setRoleName(createRoleRequestDto.getRoleName());
        return role;
    }
}
