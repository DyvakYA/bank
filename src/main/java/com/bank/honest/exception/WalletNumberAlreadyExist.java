package com.bank.honest.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by User on 7/9/2018.
 */
@Slf4j
public class WalletNumberAlreadyExist extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public WalletNumberAlreadyExist() {
        super();
    }

    public WalletNumberAlreadyExist(final String message, final Throwable cause) {
        super(message, cause);
        log.debug(message);
    }

    public WalletNumberAlreadyExist(final String message) {
        super(message);
        log.debug(message);
    }

    public WalletNumberAlreadyExist(final Throwable cause) {
        super(cause);
        log.debug(cause.toString());
    }
}
