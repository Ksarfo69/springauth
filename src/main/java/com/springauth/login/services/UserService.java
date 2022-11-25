package com.springauth.login.services;

import com.springauth.login.dto.AppUser;
import com.springauth.login.dto.AppUserRegistrationRequest;
import com.springauth.login.dto.AppUserUpdateRequest;
import com.springauth.login.dto.Role;
import com.springauth.login.repositories.AppUserRepository;
import com.springauth.login.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AppUser saveAppUser(AppUserRegistrationRequest appUserRegistrationRequest)
    {
        AppUser appUser = AppUser.builder()
                .username(appUserRegistrationRequest.username())
                .password(passwordEncoder.encode(appUserRegistrationRequest.password()))
                .build();
        return appUserRepository.save(appUser);
    }

    public Role saveRole(Role role)
    {
        return roleRepository.save(role);
    }

    public List<AppUser> fetchAllAppUsers()
    {
        return appUserRepository.findAll();
    }

    public List<Role> fetchAllRoles()
    {
        return roleRepository.findAll();
    }

    public AppUser addRoleToUser(AppUserUpdateRequest appUserUpdateRequest)
    {
        AppUser repAppUser = appUserRepository.findByUsername(appUserUpdateRequest.username());

        Role repRole = roleRepository.findByRoleName(appUserUpdateRequest.roleName());


        repAppUser.getRoles().add(repRole);

        return appUserRepository.save(repAppUser);
    }

//    public AppUser updateAppUser(String username, AppUserUpdateRequest appUserUpdateRequest)
//    {
//        AppUser repAppUser = appUserRepository.findByUsername(username);
//
//        if(Objects.nonNull(repAppUser.getRoles()))
//        {
//            repAppUser.setRoles(appUserUpdateRequest.roles());
//        }
//
//        return appUserRepository.save(repAppUser);
//    }
}
