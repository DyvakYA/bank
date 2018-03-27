package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 3/8/2018.
 */
@Slf4j
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
        log.debug(message);
    }
}
