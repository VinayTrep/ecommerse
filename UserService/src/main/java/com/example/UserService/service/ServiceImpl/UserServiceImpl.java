package com.example.UserService.service.ServiceImpl;

import com.example.UserService.dto.AssignRolesToUserDto;
import com.example.UserService.dto.UserResponseDto;
import com.example.UserService.exception.UserNotFoundException;
import com.example.UserService.model.Role;
import com.example.UserService.model.User;
import com.example.UserService.repository.RoleRepository;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDto findUserById(UUID id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserResponseDto.from(user);
    }

    @Override
    public UserResponseDto assignRoleToUser(UUID id, AssignRolesToUserDto requestto) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        List<Role> roleList = roleRepository.findAllByIdIn(requestto.getRoleId());

        Set<Role> roleSet = user.getRoles();
        roleSet.addAll(roleList);
        user.setRoles(roleSet);
        return UserResponseDto.from(userRepository.save(user));
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::from).toList();
    }
}
