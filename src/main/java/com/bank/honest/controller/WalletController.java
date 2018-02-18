package com.bank.honest.controller;

import com.bank.honest.model.dto.WalletDTO;
import com.bank.honest.model.entity.Wallet;
import com.bank.honest.model.entity.WalletStatus;
import com.bank.honest.model.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 2/13/2018.
 */
@RestController
public class WalletController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected WalletService walletService;

    @RequestMapping(value = "/wallets", method = RequestMethod.GET)
    public List<WalletDTO> wallets(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<WalletDTO> result = walletService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/wallets/{id}", method = RequestMethod.GET)
    public WalletDTO product(@PathVariable(value = "id") long wallet_id) {
        WalletDTO result = walletService.findWallet(wallet_id);
        return result;
    }

    @RequestMapping(value = "/wallets", method = RequestMethod.POST)
    public ResponseEntity<Void> createWallet(@RequestParam String name,
                                              @RequestParam String number,
                                              @RequestParam String expired,
                                              @RequestParam WalletStatus status) {

        Wallet wallet = Wallet.builder()
                .name(name)
                .number(number)
                .expired(expired)
                .status(status)
                .build();
        walletService.createWallet(wallet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/wallets/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWallet(@RequestParam(value = "id", required = false) Long id) {
        walletService.deleteWallet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/wallets/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWallet(@RequestParam(value = "id[]", required = false) Long[] ids) {
        walletService.deleteWallets(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/wallets", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateWallet(@RequestParam String name,
                                              @RequestParam String number,
                                              @RequestParam String expired,
                                              @RequestParam String status) {

        Wallet wallet = Wallet.builder()
                .name(name)
                .number(number)
                .expired(expired)
                .status(WalletStatus.valueOf(status))
                .build();
        walletService.createWallet(wallet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/wallets/search/{pattern}", method = RequestMethod.GET)
    public WalletDTO walletByPattern(@PathVariable(value = "pattern") String pattern) {
        WalletDTO result = walletService.findWallet(pattern);
        return result;
    }

}
