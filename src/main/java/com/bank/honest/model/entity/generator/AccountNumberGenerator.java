package com.bank.honest.model.entity.generator;

import com.bank.honest.model.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by User on 6/10/2018.
 */
@Slf4j
public class AccountNumberGenerator implements IdentifierGenerator {

    @Autowired
    private AccountService accountService;

    private Random r = new Random(50);


    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object o)
            throws HibernateException {

        log.info("Generator started");

        String number = String.format("%05d", r.nextLong());
        while (accountService.existByNumber(number))
            number = String.format("%05d", r.nextLong());
        return number;
    }

}

