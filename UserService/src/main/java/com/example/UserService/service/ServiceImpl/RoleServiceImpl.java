package com.example.UserService.service.ServiceImpl;

import com.example.UserService.dto.CreateRoleRequestDto;
import com.example.UserService.dto.RoleResponseDto;
import com.example.UserService.model.Role;
import com.example.UserService.repository.RoleRepository;
import com.example.UserService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleResponseDto createRole(CreateRoleRequestDto requestDto) {
        Role role = CreateRoleRequestDto.from(requestDto);
        Role savedRole = roleRepository.save(role);
        return RoleResponseDto.from(savedRole);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleResponseDto::from).toList();
    }
}
