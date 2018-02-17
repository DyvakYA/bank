package com.bank.honest.controller;

import com.bank.honest.model.dto.AccountDTO;
import com.bank.honest.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by User on 2/11/2018.
 */
@RestController
public class AccountController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected AccountService accountService;

    @RequestMapping("/accounts")
    public List<AccountDTO> accounts(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<AccountDTO> result = accountService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO accountById(@PathVariable(value = "id") long productId) {
        AccountDTO result = accountService.findByPattern(productId);
        return result;
    }

    @RequestMapping("/accounts/search/{pattern}")
    public List<AccountDTO> accountByPattern(@PathVariable(value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<AccountDTO> result = accountService.findByPattern(pattern, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }
}
