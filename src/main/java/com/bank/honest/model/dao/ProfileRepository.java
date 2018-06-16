package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Profile;
import com.bank.honest.model.entity.Wallet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 2/10/2018.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p JOIN CustomUser c ON p.id=c.profile WHERE c.phone = :phone")
    Profile findByPhone(@Param("phone") String phone);
}
