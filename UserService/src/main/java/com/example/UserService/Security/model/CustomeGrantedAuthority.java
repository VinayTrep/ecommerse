package com.example.UserService.Security.model;

import com.example.UserService.model.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@NoArgsConstructor
public class CustomeGrantedAuthority implements GrantedAuthority {
    private String authority;
    public CustomeGrantedAuthority(Role role) {
        this.authority = role.getRoleName();
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
