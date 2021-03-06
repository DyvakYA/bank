package com.bank.honest.model.service;

import com.bank.honest.model.dao.AccountRepository;
import com.bank.honest.model.dao.UserRepository;
import com.bank.honest.model.dao.WalletRepository;
import com.bank.honest.model.dto.AccountDTO;
import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.entity.Wallet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {

    private static final Logger log = Logger.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;


    @Transactional(readOnly = true)
    public Account findAccount(long account_id) {
        Account result = accountRepository.findOne(account_id);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AccountDTO> findAll(Pageable pageable) {
        log.info("START ACCOUNT SERVICE GET ALL");
        List<Account> accounts = accountRepository.findAll(pageable).getContent();
        log.info(accounts);
        List<AccountDTO> result = new ArrayList<>();
        for (Account account : accounts) {
            result.add(account.toDTO());
        }
        log.info(result);
        return result;
    }

    @Transactional
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional()
    public void updateAccount(Account account) {

        log.info("ACCOUNT SERVICE UPDATE ACCOUNT AMOUNT");
        List<Wallet> wallets = walletRepository.findWalletsByAccountId(account.getId());
        for (Wallet wallet : wallets) {
            wallet.setBlocked(account.isBlocked());
            walletRepository.saveAndFlush(wallet);
        }
        account.setWallets(wallets);
        accountRepository.save(account);
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
        List<Account> accounts = accountRepository.findAccountByPattern(pattern, pageable);
        for (Account account : accounts)
            result.add(account.toDTO());
        return result;
    }

    @Transactional
    public void deleteAccount(Long[] ids) {
        for (Long id : ids)
            accountRepository.delete(id);
    }

    public List<AccountDTO> findByUser(Long id, Pageable pageable) {
        List<AccountDTO> result = new ArrayList<>();
        List<Account> accounts = accountRepository.findByCustomUser(id);
        for (Account account : accounts)
            result.add(account.toDTO());
        return result;
    }

    public Account findByNumber(String number) {
        return accountRepository.findAccountByNumber(number);
    }

    public boolean existByNumber(String number) {
        return accountRepository.existsByAccountNumber(number);
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
