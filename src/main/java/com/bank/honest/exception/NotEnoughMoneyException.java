package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 5/12/2018.
 */
@Slf4j
public class NotEnoughMoneyException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public NotEnoughMoneyException() {
        super();
    }

    public NotEnoughMoneyException(final String message, final Throwable cause) {
        super(message, cause);
        log.debug(message);
    }

    public NotEnoughMoneyException(final String message) {
        super(message);
        log.debug(message);
    }

    public NotEnoughMoneyException(final Throwable cause) {
        super(cause);
        log.debug(cause.toString());
    }
}
