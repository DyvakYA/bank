package com.bank.honest.controller;

import com.bank.honest.exception.UserAlreadyExistException;
import com.bank.honest.exception.WalletNumberAlreadyExist;
import com.bank.honest.model.service.UserService;
import com.bank.honest.model.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checking")
public class ChakingController {

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/phone/{number}", method = RequestMethod.POST)
    public ResponseEntity<Void> phoneNumberChecking(@PathVariable(value = "number") String number) {
        if (userService.existByPhoneNumber(number)) {
            throw new UserAlreadyExistException("User with this phone number already exist");
        }
        return new ResponseEntity<>(HttpStatus.LOCKED);
    }

    @RequestMapping(value = "/wallet/{number}", method = RequestMethod.POST)
    public ResponseEntity<Void> walletNumberChecking(@PathVariable(value = "number") String number) {
        if (walletService.existByWalletNumber(number)) {
            throw new WalletNumberAlreadyExist("Wallet with this number already exist");
        }
        return new ResponseEntity<Void>(HttpStatus.LOCKED);
    }

}
