package com.bank.honest.controller;

import com.bank.honest.model.dto.AccountDTO;
import com.bank.honest.model.dto.AmountDTO;
import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.generator.NumberGeberatorUtil;
import com.bank.honest.model.service.AccountService;
import com.bank.honest.model.service.UserService;
import com.bank.honest.model.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 2/11/2018.
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected WalletService walletService;

    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDTO> accounts(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<AccountDTO> result = accountService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AccountDTO accountById(@PathVariable(value = "id") Long account_id) {
        Account account = accountService.findAccount(account_id);
        return account.toDTO();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody AccountDTO dto) {

        Account account = Account.builder()
                .number(NumberGeberatorUtil.accountNumberGenerator())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .customUser(dto.getCustomUser())
                .build();
        System.out.println(account);
        accountService.createAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id[]") Long[] ids) {
        accountService.deleteAccount(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody AccountDTO dto) {

        Account account = accountService.findAccount(dto.getId());
        account.setAmount(dto.getAmount());
        account.setBlocked(dto.isBlocked());

//        account = Account.builder()
//                .id(dto.getId())
//                .number(dto.getNumber())
//                .amount(dto.getAmount())
//                .currency(dto.getCurrency())
//                .customUser(userService.findUserByAccount(dto.getId()))
//                .isBlocked(dto.isBlocked())
//                .build();
        accountService.updateAccount(account);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAccountAmount(@Valid @RequestBody AmountDTO dto) {

        Account account = accountService.findAccount(dto.getId());
        account.setAmount(dto.getAmount());
        account.setBlocked(dto.isBlocked());
        accountService.updateAccount(account);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/search/{pattern}", method = RequestMethod.GET)
    public List<AccountDTO> accountByPattern(@PathVariable(value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<AccountDTO> result = accountService.findByPattern(pattern, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public List<AccountDTO> accountsByUser(@PathVariable(value = "id") Long id, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<AccountDTO> result = accountService.findByUser(id, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }
}
