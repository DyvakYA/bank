package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 3/27/2018.
 */
@Slf4j
public class TokenIsIncorrectException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public TokenIsIncorrectException() {
        super();
    }

    public TokenIsIncorrectException(final String message, final Throwable cause) {
        super(message, cause);
        log.debug(message);
    }

    public TokenIsIncorrectException(final String message) {
        super(message);
        log.debug(message);
    }

    public TokenIsIncorrectException(final Throwable cause) {
        super(cause);
        log.debug(cause.toString());
    }

}
