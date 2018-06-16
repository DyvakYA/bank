package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Wallet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 2/18/2018.
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    //    @Query("SELECT w FROM Wallet w INNER JOIN Account a ON a.id=w.account_id WHERE a.number=:number")
    //SELECT Orders.OrderID, Orders.CustomerID, Orders.OrderDate
//FROM Orders
//    INNER JOIN Customers
//    ON Orders.CustomerID=Customers.CustomerID
//    Where Customers.CustomerId = 3;

    @Query("SELECT w FROM Wallet w JOIN Account a ON w.account=a.id WHERE a.number = :number")
    List<Wallet> findByPattern(@Param("number") String number, Pageable pageable);

    @Query("SELECT w FROM Wallet w JOIN Account a ON w.account=a.id WHERE a.id = :id")
    List<Wallet> findWalletsByAccountId(@Param("id") Long id);
}
