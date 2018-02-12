package com.bank.honest.model.service;

import com.bank.honest.model.dao.AccountRepository;
import com.bank.honest.model.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 1/8/2018.
 */
public abstract class BaseService {

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected UserRepository userRepository;

    @Transactional(readOnly = true)
    public long count() {
        return accountRepository.count();
    }

//    @Transactional(readOnly = true)
//    public long countByUser(CustomUser customUser) {
//        return accountRepository.countByUser(customUser);
//    }
}
