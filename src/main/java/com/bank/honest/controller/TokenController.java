package com.bank.honest.controller;

import com.bank.honest.model.dto.AuthenticateDTO;
import com.bank.honest.security.JWTGenerator;
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

    public TokenController(JWTGenerator jwtGenerator){
        this.jwtGenerator = jwtGenerator;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String generate(@RequestBody AuthenticateDTO authenticateDTO){

        System.out.println(authenticateDTO.toString());

        JWTGenerator jwtGenerator = new JWTGenerator();
        return jwtGenerator.generate(null);
    }
}
