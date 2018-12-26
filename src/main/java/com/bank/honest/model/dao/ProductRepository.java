package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Product;
import com.bank.honest.model.entity.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by User on 2/11/2018.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Product u WHERE u.currency = :currency")
    boolean existsProductByCurrency(@Param("currency") Currency currency);

    @Query("SELECT u FROM Product u where u.currency = :currency")
    Product findProductByCurrency(@Param("currency") Currency currency);
}
