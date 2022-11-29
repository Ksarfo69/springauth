package com.springauth.login.security;

import com.springauth.login.dto.AppUser;
import com.springauth.login.repositories.AppUserRepository;
import com.springauth.login.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DaoUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);

        //throw if appUser not null
        if(Objects.isNull(appUser))
        {
            throw new UsernameNotFoundException("User not found");
        }

        // create authorities
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        appUser.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName()))
        );


        // return user with authorities
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}
