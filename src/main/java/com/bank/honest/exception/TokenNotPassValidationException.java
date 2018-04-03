package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 4/2/2018.
 */
@Slf4j
public class TokenNotPassValidationException extends RuntimeException {

    public TokenNotPassValidationException(final String message, final Throwable cause) {
        super(message, cause);
        log.debug(message);
    }
}
