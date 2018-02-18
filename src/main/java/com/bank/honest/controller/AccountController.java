package com.bank.honest.controller;

import com.bank.honest.model.dto.AccountDTO;
import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.Currency;
import com.bank.honest.model.service.AccountService;
import com.bank.honest.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 2/11/2018.
 */
@RestController
public class AccountController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected UserService userService;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<AccountDTO> accounts(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<AccountDTO> result = accountService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public AccountDTO accountById(@PathVariable(value = "id") long account_id) {
        AccountDTO result = accountService.findAccount(account_id);
        return result;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity<Void> createAccount(@RequestParam String number,
                                              @RequestParam Long amount,
                                              @RequestParam String currency,
                                              @RequestParam String user_id) {

        Account account = Account.builder()
                .number(number)
                .amount(amount)
                .currency(Currency.valueOf(currency))
                .customUser(userService.findUser(user_id))
                .build();
        accountService.createAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@RequestParam(value = "id", required = false) Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAcounts(@RequestParam(value = "id[]", required = false) Long[] ids) {
        accountService.deleteAccount(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAccount(@RequestParam String number,
                                              @RequestParam Long amount,
                                              @RequestParam String currency,
                                              @RequestParam String user_id) {

        Account account = Account.builder()
                .number(number)
                .amount(amount)
                .currency(Currency.valueOf(currency))
                .customUser(userService.findUser(user_id))
                .build();
        accountService.createAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/search/{pattern}", method = RequestMethod.GET)
    public List<AccountDTO> accountByPattern(@PathVariable(value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<AccountDTO> result = accountService.findByPattern(pattern, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }
}
