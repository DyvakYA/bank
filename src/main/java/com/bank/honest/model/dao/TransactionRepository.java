package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/21/2018.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t where u.date = :date")
    List<Transaction> findByDate(Date date);

    @Query("SELECT t FROM Transaction t WHERE t.date >= :from AND t.date <= :to  ")
    List<Transaction> findByPeriod(@Param("from")Date from, @Param("to")Date to);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Transaction u WHERE u.number = :number")
    boolean existsByNumber(@Param("number") String number);
}
