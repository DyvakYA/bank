package com.bank.honest.model.service;

import com.bank.honest.model.dao.ProfileRepository;
import com.bank.honest.model.dto.ProfileDTO;
import com.bank.honest.model.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/13/2018.
 */
@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Transactional
    public ProfileDTO findUserProfile(long id) {
        Profile profile = profileRepository.findOne(id);
        ProfileDTO result = profile.toDTO();
        return result;
    }

    @Transactional(readOnly = true)
    public List<ProfileDTO> findAll(Pageable pageable) {
        List<ProfileDTO> result = new ArrayList<>();
        List<Profile> list = profileRepository.findAll(pageable).getContent();
        for (Profile profile : list)
            result.add(profile.toDTO());
        return result;
    }

    @Transactional(readOnly = true)
    public ProfileDTO findProfile(Long profile_id) {
        Profile profile = profileRepository.findOne(profile_id);
        return profile.toDTO();
    }

    @Transactional
    public void createProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Transactional
    public void deleteProfiles(Long[] ids) {
        for (Long id : ids)
        profileRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public ProfileDTO findByPhone(String phone) {
        Profile profile = profileRepository.findByPhone(phone);
        return profile.toDTO();
    }
}
