package com.bank.honest.model.service;

import com.bank.honest.model.dao.UserProfileRepository;
import com.bank.honest.model.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2/13/2018.
 */
@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    public void addUserProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    public UserProfile findUserProfile(long id) {
        return userProfileRepository.findOne(id);
    }




}
