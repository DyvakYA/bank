package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 3/27/2018.
 */
@Slf4j
public class UserAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
        log.debug(message);
    }

    public UserAlreadyExistException(final String message) {
        super(message);
        log.debug(message);
    }

    public UserAlreadyExistException(final Throwable cause) {
        super(cause);
        log.debug(cause.toString());
    }

}
