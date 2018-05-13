package com.bank.honest.controller;

import com.bank.honest.model.dto.WalletDTO;
import com.bank.honest.model.entity.Wallet;
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
 * Created by User on 2/13/2018.
 */
@RestController
@RequestMapping("/wallets")
public class WalletController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected WalletService walletService;

    @RequestMapping(method = RequestMethod.GET)
    public List<WalletDTO> wallets(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<WalletDTO> result = walletService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WalletDTO wallet(@PathVariable(value = "id") Long wallet_id) {
        WalletDTO result = walletService.findWallet(wallet_id);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody WalletDTO dto) {

        Wallet wallet = Wallet.builder()
                .name(dto.getName())
                .number(dto.getNumber())
                .expired(dto.getExpiration())
                .build();
        walletService.createWallet(wallet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id", required = false) Long id) {
        walletService.deleteWallet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id[]", required = false) Long[] ids) {
        walletService.deleteWallets(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody WalletDTO dto, @RequestParam Long id) {

        Wallet wallet = Wallet.builder()
                .id(id)
                .name(dto.getName())
                .number(dto.getNumber())
                .expired(dto.getExpiration())
                .build();
        walletService.createWallet(wallet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{account_number}", method = RequestMethod.GET)
    public List<WalletDTO> walletByPattern(@PathVariable(value = "account_number") String account_number, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<WalletDTO> result = walletService.findWallet(account_number, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return result;
    }

    @RequestMapping(value = "/account/wallets/{id}", method = RequestMethod.GET)
    public List<WalletDTO> walletsByAccountId(@PathVariable(value = "id") Long id) {
        List<WalletDTO> result = walletService.findWalletsByAccountId(id);
        return result;
    }
}
