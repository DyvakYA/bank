package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 3/27/2018.
 */
@Slf4j
public class TokenIsMissingException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public TokenIsMissingException() {
        super();
    }

    public TokenIsMissingException(final String message, final Throwable cause) {
        super(message, cause);
        log.debug(message);
    }

    public TokenIsMissingException(final String message) {
        super(message);
        log.debug(message);
    }

    public TokenIsMissingException(final Throwable cause) {
        super(cause);
        log.debug(cause.toString());
    }

}
