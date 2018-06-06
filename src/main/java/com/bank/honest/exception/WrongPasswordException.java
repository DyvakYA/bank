package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 6/6/2018.
 */
@Slf4j
public class WrongPasswordException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(final String message, final Throwable cause) {
        super(message, cause);
        log.debug(message);
    }

    public WrongPasswordException(final String message) {
        super(message);
        log.debug(message);
    }

    public WrongPasswordException(final Throwable cause) {
        super(cause);
        log.debug(cause.toString());
    }
}
