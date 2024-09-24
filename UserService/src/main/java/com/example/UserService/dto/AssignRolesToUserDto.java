package com.example.UserService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AssignRolesToUserDto {
    private List<UUID> roleId;
}
