package com.bank.honest.controller;

import com.bank.honest.model.dto.ProfileDTO;
import com.bank.honest.model.entity.Profile;
import com.bank.honest.model.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 2/17/2018.
 */
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProfileDTO> profiles(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<ProfileDTO> profiles = profileService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return profiles;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProfileDTO user(@PathVariable(value = "id") Long profile_id) {
        ProfileDTO profile = profileService.findProfile(profile_id);
        return profile;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody ProfileDTO dto) {

        Profile profile = Profile.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
        profileService.createProfile(profile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id[]") Long[] ids) {
        if (ids != null && ids.length > 0)
            profileService.deleteProfiles(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> userUpdate(@Valid @RequestBody ProfileDTO dto, @RequestParam Long id) {

        Profile profile = Profile.builder()
                .id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
        profileService.createProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{phone}", method = RequestMethod.GET)
    public ProfileDTO userByPhone(@PathVariable(value = "phone") String phone) {
        ProfileDTO result = profileService.findByPhone(phone);
        return result;
    }
}
