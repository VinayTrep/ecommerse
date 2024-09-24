package com.example.UserService.controller;

import com.example.UserService.dto.AssignRolesToUserDto;
import com.example.UserService.dto.UserResponseDto;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/{id}/role")
    public ResponseEntity<UserResponseDto> addRoleToUser(@PathVariable("id") UUID id,
                                              @RequestBody AssignRolesToUserDto requestDto) {
        return  ResponseEntity.ok(userService.assignRoleToUser(id,requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
