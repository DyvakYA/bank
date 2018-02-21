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

    @Query("SELECT c FROM Wallet c WHERE LOWER(c.number) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Wallet> findByPattern(String pattern, Pageable pageable);
}
