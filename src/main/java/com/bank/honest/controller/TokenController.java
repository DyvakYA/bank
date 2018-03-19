package com.bank.honest.controller;

import com.bank.honest.security.JWTGenerator;
import com.bank.honest.security.JWTUser;
import org.springframework.web.bind.annotation.*;

/**
 * Created by User on 3/19/2018.
 */
@RestController
public class TokenController {

    @PostMapping("/login")
    public String generate(@RequestBody final JWTUser jwtUser){

        System.out.println(jwtUser.toString());

        JWTGenerator jwtGenerator = new JWTGenerator();
        return jwtGenerator.generate(jwtUser);
    }
}
