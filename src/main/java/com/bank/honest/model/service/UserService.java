package com.bank.honest.model.service;

import com.bank.honest.model.dao.AccountRepository;
import com.bank.honest.model.dao.ProfileRepository;
import com.bank.honest.model.dao.UserRepository;
import com.bank.honest.model.dto.RegistrationDTO;
import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.entity.Profile;
import com.bank.honest.model.entity.enums.Currency;
import com.bank.honest.model.entity.enums.UserRole;
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

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public void createUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Transactional
    public boolean existByPhone(String phone) {
        return userRepository.existsByPhone(phone);
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
    public CustomUser findUserByPhone(String phone) {
        CustomUser user = userRepository.findByPhone(phone);
        return user;
    }

    @Transactional(readOnly = true)
    public List<CustomUser> findAllUsersForAdmin() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(Pageable pageable) {
        List<CustomUser> customUsers = userRepository.findAll(pageable).getContent();
        List<UserDTO> result = new ArrayList<>();
        for (CustomUser customUser : customUsers) {
            result.add(customUser.toDTO());
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<CustomUser> customUsers = userRepository.findAll();
        List<UserDTO> result = new ArrayList<>();
        for (CustomUser customUser : customUsers) {
            result.add(customUser.toDTO());
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<CustomUser> findAllUsersForAdmin(Pageable pageable) {
        List<CustomUser> result = userRepository.findAll(pageable).getContent();
        return result;
    }

    @Transactional(readOnly = true)
    public UserDTO findUser(Long userId) {
        CustomUser user = userRepository.findOne(userId);
        UserDTO result = user.toDTO();
        return result;
    }

    @Transactional(readOnly = true)
    public long count() {
        return userRepository.count();
    }

    @Transactional
    public void deleteUsers(Long[] toDelete) {
        for (long id : toDelete)
            userRepository.delete(id);
    }

    @Transactional
    public void deleteUsers(Long id) {
        userRepository.delete(id);
    }

    @Transactional
    public void registration(RegistrationDTO registrationDTO) {

        Profile profile = Profile.builder()
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .build();
        profileRepository.save(profile);

        CustomUser user = CustomUser.builder()
                .phone(registrationDTO.getPhone())
                .password(registrationDTO.getPassword())
                .profile(profile)
                .role(UserRole.USER)
                .build();
        userRepository.save(user);

        Account accountUAH = Account.builder()
                .customUser(user)
                .number(user.getPhone() + "1")
                .amount(0L)
                .currency(Currency.UAH)
                .isBlocked(false)
                .build();
        accountRepository.save(accountUAH);

        Account accountUSD = Account.builder()
                .customUser(user)
                .number(user.getPhone() + "2")
                .amount(0L)
                .currency(Currency.USD)
                .isBlocked(false)
                .build();
        accountRepository.save(accountUSD);
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
//    public Map<User, Long> findAllUsersForAdmin(Pageable pageable) {
//        List<User> users = userRepository.findAllUsersForAdmin(pageable).getContent();
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
//    public List<User> findAllUsersForAdmin(Pageable pageable) {
//        return userRepository.findAllUsersForAdmin(pageable).getContent();
//    }


}
