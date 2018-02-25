package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Wallet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 2/18/2018.
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

    @Query("SELECT * FROM Wallet w INNER JOIN Account a ON a.id=w.account_id WHERE a.number=:number")
    List<Wallet> findByPattern(String number, Pageable pageable);
}
