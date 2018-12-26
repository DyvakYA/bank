package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT c FROM Account c WHERE c.number = :number")
    Account findAccountByNumber(@Param("number") String number);

    @Query("SELECT c FROM Account c WHERE c.number LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Account> findAccountByPattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Account u WHERE u.number = :number")
    boolean existsByAccountNumber(@Param("number") String number);

    @Query("SELECT c FROM Account c WHERE c.customUser.id = :id")
    List<Account> findByCustomUser(@Param("id") Long id);
}
