package com.example.UserService.controller;

import com.example.UserService.dto.CreateRoleRequestDto;
import com.example.UserService.dto.RoleResponseDto;
import com.example.UserService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<RoleResponseDto> addRole(@RequestBody CreateRoleRequestDto requestDto) {
        return ResponseEntity.ok(roleService.createRole(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

}
