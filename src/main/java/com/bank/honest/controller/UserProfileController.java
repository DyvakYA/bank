package com.bank.honest.controller;

import com.bank.honest.model.dto.UserProfileDTO;
import com.bank.honest.model.entity.UserProfile;
import com.bank.honest.model.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User on 2/17/2018.
 */
@RestController
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public UserProfileDTO getProfile(@RequestParam("id") long id){
        UserProfile profile = userProfileService.findUserProfile(id);
        UserProfileDTO result = profile.toDTO();
        return result;
    }
}
