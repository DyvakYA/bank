package com.bank.honest.controller;

import com.bank.honest.model.dto.ProfileDTO;
import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.Profile;
import com.bank.honest.model.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 2/17/2018.
 */
@RestController
public class ProfileController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    ProfileService profileService;

    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    public List<ProfileDTO> profiles(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<ProfileDTO> profiles = profileService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return profiles;
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.GET)
    public ProfileDTO user(@PathVariable(value = "id") Long profile_id) {
        ProfileDTO profile = profileService.findProfile(profile_id);
        return profile;
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String email) {

        Profile profile = Profile.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
        profileService.createProfile(profile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id", required = false) Long id) {
        profileService.deleteProfiles(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/profiles/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id[]", required = false) Long[] ids) {
        if (ids != null && ids.length > 0)
            profileService.deleteProfiles(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> userUpdate(@RequestParam Long id,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam String email) {

        Profile profile = Profile.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
        profileService.createProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/profiles/search/{name}", method = RequestMethod.GET)
    public UserDTO userByPhone(@PathVariable(value = "name") String name) {
        UserDTO result = profileService.findByName(name);
        return result;
    }
}
