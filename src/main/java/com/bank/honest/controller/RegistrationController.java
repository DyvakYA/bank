package com.bank.honest.controller;

import com.bank.honest.exception.UserAlreadyExistException;
import com.bank.honest.model.dto.RegistrationDTO;
import com.bank.honest.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by User on 6/16/2018.
 */
@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<Void> registration(@Valid @RequestBody RegistrationDTO dto) {

        if (userService.existByPhone(dto.getPhone())) {
            throw new UserAlreadyExistException("User with this phone number already exist");
        } else {
            userService.registration(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
