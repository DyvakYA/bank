package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
//    @Query("SELECT c FROM Account c WHERE c.user = :user")
//    List<Account> findByUser(@Param("user") CustomUser customUser, Pageable pageable);

//    @Query("SELECT COUNT(c) FROM Account c WHERE c.user = :user")
//    long countByUser(@Param("user") CustomUser customUser);

    @Query("SELECT c FROM Account c WHERE LOWER(c.number) LIKE LOWER(CONCAT('%', :pattern, '%'))")
    List<Account> findByPattern(@Param("pattern") String pattern, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Account u WHERE u.number = :number")
    boolean existsByAccountNumber(@Param("number") String number);

}
