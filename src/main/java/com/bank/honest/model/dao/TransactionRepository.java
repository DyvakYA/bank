package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/21/2018.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT u FROM Transaction u where u.date = :date")
    List<Transaction> findByDate(Date date);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Transaction u WHERE u.number = :number")
    boolean existsByNumber(@Param("number") String number);
}
