package com.springauth.login.services;

import com.springauth.login.dto.Role;

import java.util.List;

public record AppUserUpdateRequest(
        String username,
        String roleName,
        List<Role> roles
) {
}
