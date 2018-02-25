package com.bank.honest.model.service;

import com.bank.honest.model.dao.WalletRepository;
import com.bank.honest.model.dto.WalletDTO;
import com.bank.honest.model.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/18/2018.
 */
@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Transactional
    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Transactional
    public void deleteWallets(Long[] ids) {
        for (Long id : ids)
            walletRepository.delete(id);
    }

    @Transactional
    public void deleteWallet(Long id) {
        walletRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public WalletDTO findWallet(long wallet_id) {
        Wallet wallet = walletRepository.findOne(wallet_id);
        WalletDTO result = wallet.toDTO();
        return result;
    }

    @Transactional(readOnly = true)
    public List<WalletDTO> findAll(Pageable pageable) {
        List<WalletDTO> result = new ArrayList<>();
        List<Wallet> wallets = walletRepository.findAll(pageable).getContent();
        for (Wallet wallet : wallets)
            result.add(wallet.toDTO());
        return result;
    }

    public List<WalletDTO> findWallet(String account_number, Pageable pageable) {
        List<WalletDTO> result = new ArrayList<>();
        List<Wallet> wallets = walletRepository.findByPattern(account_number, pageable);
        for (Wallet wallet : wallets)
            result.add(wallet.toDTO());
        return result;
    }
}
