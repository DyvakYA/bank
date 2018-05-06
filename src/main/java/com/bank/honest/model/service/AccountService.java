package com.bank.honest.model.service;

import com.bank.honest.model.dao.AccountRepository;
import com.bank.honest.model.dao.UserRepository;
import com.bank.honest.model.dto.AccountDTO;
import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public AccountDTO findAccount(long account_id) {
        Account account = accountRepository.findOne(account_id);
        AccountDTO result = account.toDTO();
        return result;
    }

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> findAll(Pageable pageable) {
        List<Account> accounts = accountRepository.findAll(pageable).getContent();
        List<AccountDTO> result = new ArrayList<>();
        for (Account account : accounts) {
            result.add(account.toDTO());
        }
        return result;
    }

    @Transactional
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public boolean createAccount(String number, long amount, CustomUser customUser) {
        if (accountRepository.existsByAccountNumber(number))
            return false;

        Account account = Account.builder()
                .number(number)
                .amount(amount)
                .customUser(customUser)
                .build();
        accountRepository.save(account);
        return true;
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> findUserAccounts(String phone) {
        CustomUser user = userRepository.findByPhone(phone);
        List<Account> accounts = user.getAccounts();
        List<AccountDTO> result = new ArrayList<>();
        for (Account account : accounts)
            result.add(account.toDTO());
        return result;
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> findByPattern(String pattern, Pageable pageable) {
        List<AccountDTO> result = new ArrayList<>();
        List<Account> accounts = accountRepository.findByPattern(pattern, pageable);
        for (Account account : accounts)
            result.add(account.toDTO());
        return result;
    }

    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.delete(id);
    }

    @Transactional
    public void deleteAccount(Long[] ids) {
        for (Long id : ids)
            accountRepository.delete(id);
    }

    public List<AccountDTO> findByUser(Long id, Pageable pageable) {
        List<AccountDTO> result = new ArrayList<>();
        List<Account> accounts = accountRepository.findByUser(id, pageable);
        for (Account account : accounts)
            result.add(account.toDTO());
        return result;
    }

    public Account findByNumber(String number) {
        return accountRepository.findAccountByNumber(number);
    }

//    @Transactional(readOnly = true)
//    public long countByUser(User user) {
//        return accountRepository.countByUser(user);
//    }

//
//    @Transactional(readOnly = true)
//    public List<Contact> findByGroup(Group group, Pageable pageable) {
//        return contactRepository.findByGroup(group, pageable);
//    }
//
}
