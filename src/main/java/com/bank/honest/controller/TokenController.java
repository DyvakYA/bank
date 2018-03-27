package com.bank.honest.controller;

import com.bank.honest.model.dto.AuthenticateDTO;
import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.entity.UserRole;
import com.bank.honest.model.service.UserService;
import com.bank.honest.security.JWTGenerator;
import com.bank.honest.security.JWTUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 3/19/2018.
 */
@RestController
public class TokenController {

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private UserService userService;

    public TokenController(JWTGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String generate(@RequestBody AuthenticateDTO authenticateDTO) {

        System.out.println(authenticateDTO.toString());

//        if (!userService.existByPhone(authenticateDTO.getPhone())) {
//            throw new UserNotFoundException("User not find");
//        } else {
            CustomUser user = userService.findUserByPhone(authenticateDTO.getPhone());
            JWTUser jwtUser = JWTUser.builder()
                    .id(user.getId())
                    .phone(user.getPhone())
                    .role(user.getRole()==null ? UserRole.USER : user.getRole())
                    .build();

            return jwtGenerator.generate(jwtUser);
//        }
    }
}
