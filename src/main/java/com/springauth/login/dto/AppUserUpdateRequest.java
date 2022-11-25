package com.springauth.login.dto;

import com.springauth.login.dto.Role;

import java.util.List;

public record AppUserUpdateRequest(
        String username,
        String roleName,
        List<Role> roles
) {
}
