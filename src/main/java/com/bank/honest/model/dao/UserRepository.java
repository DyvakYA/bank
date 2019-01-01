package com.bank.honest.model.dao;

import com.bank.honest.model.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

    @Query(name = "existsByLogin", value = "SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM CustomUser u WHERE u.phone = :phone")
    boolean existsByPhone(@Param("phone") String phone);

    @Query("SELECT u FROM CustomUser u where u.phone = :phone")
    CustomUser findByPhone(@Param("phone") String phone);

    //@Query("SELECT u FROM CustomUser u JOIN u.accounts a ON a.customUser.id=u.id WHERE a.id = :id")
    //@Query("SELECT u FROM CustomUser u WHERE u.accounts.id = :id")
    @Query("FROM CustomUser AS u LEFT JOIN u.accounts AS a WHERE a.id = :id")
    CustomUser findCustomUserByAccounts(@Param("id") Long id);
}
