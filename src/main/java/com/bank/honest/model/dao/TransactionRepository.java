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

    @Query("SELECT t FROM Transaction t WHERE t.date LIKE :date")
    List<Transaction> findTransactionsByDate(@Param("date") Date date);

    @Query("SELECT t FROM Transaction t WHERE t.date >= :dateFrom AND t.date <= :dateTo")
    List<Transaction> findTransactionsByPeriod(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Transaction u WHERE u.number = :number")
    boolean existsByNumber(@Param("number") String number);

    @Query("select t from Transaction t where t.number = :number")
    Transaction findTransactionByNumber(@Param("number") String number);
}
