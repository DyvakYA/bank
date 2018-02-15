package com.bank.honest.model.service;

import com.bank.honest.model.entity.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2/11/2018.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        CustomUser customUser = userService.findUser(phone);
        if (customUser == null) {
            throw new UsernameNotFoundException(phone + " not found");
        }
        System.out.println("22222");
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(customUser.getRole().toString()));

        return new User(customUser.getPhone(), customUser.getPassword(), roles);
    }
}
