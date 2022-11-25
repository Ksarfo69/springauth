package com.springauth.login.controllers;

import com.springauth.login.services.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token")
    public String token(Authentication authentication)
    {
        log.info("Generating token for user: {}", authentication.getPrincipal());
        String token = tokenService.generateToken(authentication);
        log.info("Token generated for user: {}, token: {}", authentication.getPrincipal(), token);
        return token;
    }
}
