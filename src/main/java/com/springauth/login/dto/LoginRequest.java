package com.springauth.login.dto;

public record LoginRequest(
        String username,
        String password
) {
}
