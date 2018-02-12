package com.bank.honest.model.dao;

import com.bank.honest.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 2/10/2018.
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
