package com.bank.honest.model.dao;

import com.bank.honest.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by User on 2/10/2018.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.customUser.phone = :phone")
    Profile findProfileByUserPhone(@Param("phone") String phone);

//    @Query("SELECT p FROM Profile p WHERE p.customUser.phone = :phone")
//    List<Profile> findProfileByUserPhoneQW(@Param("phone") String phone);
}
