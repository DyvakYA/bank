package com.bank.honest.model.service;

import com.bank.honest.model.dao.UserRepository;
import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 1/8/2018.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void addUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Transactional
    public boolean addUser(String phone, String passHash, UserRole role) {
        if (userRepository.existsByPhone(phone))
            return false;

        CustomUser customUser = CustomUser.builder()
                .phone(phone)
                .password(passHash)
                .role(role)
                .build();
        userRepository.save(customUser);

        return true;
    }

    @Transactional(readOnly = true)
    public CustomUser findUser(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Transactional(readOnly = true)
    public UserDTO findByPhone(String phone) {
        CustomUser user = userRepository.findByPhone(phone);
        UserDTO result = user.toDTO();
        return result;
    }

    @Transactional(readOnly = true)
    public List<CustomUser> findAll() {
        return userRepository.findAll();
    }

    public List<UserDTO> findAll(Pageable pageable) {
        List<CustomUser> customUsers = userRepository.findAll(pageable).getContent();
        List<UserDTO> result = new ArrayList<>();
        for (CustomUser customUser : customUsers) {
            result.add(customUser.toDTO());
        }
        return result;
    }

    public UserDTO findUser(Long userId) {
        CustomUser user = userRepository.findOne(userId);
        UserDTO result = user.toDTO();
        return result;
    }

    @Transactional(readOnly = true)
    public long count() {
        return userRepository.count();
    }

    public void deleteUsers(Long[] toDelete) {
        for (long id : toDelete)
            userRepository.delete(id);
    }

    public void deleteUsers(Long id) {
        userRepository.delete(id);
    }

    //    @Transactional
//    public void User(long[] toDelete) {
//        for (long id : toDelete)
//            groupRepository.delete(id);
//    }

//    @Transactional
//    public void deleteUserWithAccounts(long[] toDelete) {
//        for (long id : toDelete) {
//            CustomUser customUser = userRepository.findOne(id);
//            userRepository.delete(id);
//            List<Account> accounts = customUser.getAccounts();
//            for (Account account : accounts)
//                userRepository.delete(account);
//        }
//    }

//    @Transactional(readOnly = true)
//    public Map<User, Long> findAll(Pageable pageable) {
//        List<User> users = userRepository.findAll(pageable).getContent();
//        Map<User, Long> countByGroup = new HashMap<>();
//        for (User user : users) {
//            countByGroup.put(user, Long.valueOf(user.getAccounts().size()));
//        }
//        return countByGroup;
//    }

//    @Transactional(readOnly = true)
//    public CustomUser findGroup(long id) {
//        return groupRepository.findOne(id);
//    }

//    @Transactional
//    public void updateUser(String phone, String email, String phone) {
//        User user = userRepository.findByPhone(phone);
//
//        user.setEmail(email);
//        user.setPhone(phone);
//
//        userRepository.save(user);
//    }
//
//    @Transactional(readOnly = true)
//    public List<User> findAll(Pageable pageable) {
//        return userRepository.findAll(pageable).getContent();
//    }


}
