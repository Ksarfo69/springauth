package com.springauth.login.controllers;

import com.springauth.login.dto.AppUser;
import com.springauth.login.dto.Role;
import com.springauth.login.services.AppUserUpdateRequest;
import com.springauth.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Home {

    @Autowired
    private UserService userService;

    @PostMapping("/appuser")
    public AppUser saveAppUser(@RequestBody AppUser appUser)
    {
        return userService.saveAppUser(appUser);
    }

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role)
    {
        return userService.saveRole(role);
    }

    @GetMapping("/appuser")
    public List<AppUser> fetchAllAppUsers()
    {
        return userService.fetchAllAppUsers();
    }

    @GetMapping("/role")
    public List<Role> fetchAllRoles()
    {
        return userService.fetchAllRoles();
    }

    @PostMapping("/user/role/add")
    public AppUser addRoleToUser(@RequestBody AppUserUpdateRequest appUserUpdateRequest)
    {
        return userService.addRoleToUser(appUserUpdateRequest);
    }

}
