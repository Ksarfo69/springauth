package com.springauth.login.controllers;

import com.springauth.login.dto.LoginRequest;
import com.springauth.login.security.DaoUserService;
import com.springauth.login.services.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DaoUserService daoUserService;

    @PostMapping("/login")
    public String token(@RequestBody LoginRequest request)
    {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        log.info("Generating token for user: {}", authentication.getPrincipal());
        String token = tokenService.generateToken(authentication);
        log.info("Token generated for user: {}, token: {}", authentication.getPrincipal(), token);
        return token;
    }
}
