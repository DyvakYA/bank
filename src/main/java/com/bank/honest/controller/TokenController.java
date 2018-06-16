package com.bank.honest.controller;

import com.bank.honest.exception.UserNotFoundException;
import com.bank.honest.exception.WrongPasswordException;
import com.bank.honest.security.JWTAuthenticateDTO;
import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.entity.enums.UserRole;
import com.bank.honest.model.service.UserService;
import com.bank.honest.security.JWTGenerator;
import com.bank.honest.security.JWTUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 3/19/2018.
 */
@Slf4j
@RestController
public class TokenController {

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String generate(@RequestBody JWTAuthenticateDTO authenticateDTO) {
        if (!userService.existByPhone(authenticateDTO.getPhone())) {
            throw new UserNotFoundException("User not found");
        }
        if (!userService.findByPhone(authenticateDTO.getPhone()).getPassword().equals(authenticateDTO.getPassword())){
            throw new WrongPasswordException("Wrong password");
        } else{
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            CustomUser user = userService.findUserByPhone(authenticateDTO.getPhone());
            JWTUser jwtUser = JWTUser.builder()
                    .id(user.getId())
                    .phone(user.getPhone())
                    .role(user.getRole() == null ? UserRole.USER : user.getRole())
                    .build();

            return jwtGenerator.generate(jwtUser);
        }
    }
}
