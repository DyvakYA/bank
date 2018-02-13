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
    public AccountDTO findAccount(long accountId) {
        Account account = accountRepository.findOne(accountId);
        AccountDTO result = account.toDTO();
        return result;
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
    public boolean addAccount(String number, long amount, CustomUser customUser) {
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
    public List<AccountDTO> findAccount(String phone) {
        CustomUser user = userRepository.findByPhone(phone);
        List<Account> accounts = user.getAccounts();
        List<AccountDTO> result = new ArrayList<>();
        for(Account account: accounts)
            result.add(account.toDTO());
        return result;
    }

//    @Transactional(readOnly = true)
//    public long countByUser(User user) {
//        return accountRepository.countByUser(user);
//    }

//    @Transactional
//    public void addContact(Contact contact) {
//        contactRepository.save(contact);
//    }
//
//    @Transactional
//    public void deleteContacts(long[] idList) {
//        for (long id : idList)
//            contactRepository.delete(id);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Contact> findAll(Pageable pageable) {
//        return contactRepository.findAll(pageable).getContent();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Contact> findAll() {
//        return contactRepository.findAll();
//    }
//
//    @Transactional(readOnly = true)
//    public List<Contact> findByGroup(Group group, Pageable pageable) {
//        return contactRepository.findByGroup(group, pageable);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Contact> findByPattern(String pattern, Pageable pageable) {
//        return contactRepository.findByPattern(pattern, pageable);
//    }
//
//    public void export(List<Contact> contacts) {
//        String text = "";
//        for (Contact contact : contacts) {
//            text = text + contact.toString() + "\n";
//        }
//        try (FileWriter writer = new FileWriter("D:\\csv.txt", false)) {
//            writer.write(text);
//            writer.flush();
//        } catch (IOException ex) {
//            //NOP
//        }
//    }


}
