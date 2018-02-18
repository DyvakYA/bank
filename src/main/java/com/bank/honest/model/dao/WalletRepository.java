package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 2/18/2018.
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

    Wallet findByPattern(String pattern);
}
