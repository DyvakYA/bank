package com.bank.honest.controller;

import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.entity.UserProfile;
import com.bank.honest.model.entity.UserRole;
import com.bank.honest.model.service.UserProfileService;
import com.bank.honest.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 1/21/2018.
 */
@RestController
public class UserController {

    final int DEFAULT_GROUP_ID = -1;
    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserProfileService userProfileService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDTO> users(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<UserDTO> users = userService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return users;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDTO userById(
            @PathVariable(value = "id") Long userId) {
        UserDTO result = userService.findUser(userId);
        return result;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Void> userCreate(@RequestParam String phone,
                                           @RequestParam String password,
                                           @RequestParam String role,
                                           @RequestParam String email,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName) {

        UserRole userRole = (role != UserRole.ADMIN.toString()) ? UserRole.USER : UserRole.ADMIN;

        CustomUser user = CustomUser.builder()
                .phone(phone)
                .password(password)
                .role(userRole)
                .build();
        userService.addUser(user);

        UserProfile userProfile = UserProfile.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        userProfileService.addUserProfile(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUsers(@RequestParam(value = "id[]", required = false) Long[] ids) {
        if (ids != null && ids.length > 0)
            userService.deleteUsers(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", required = false) Long id) {
        userService.deleteUsers(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> userUpdate(@RequestParam String phone,
                                           @RequestParam String password,
                                           @RequestParam String role,
                                           @RequestParam String email,
                                           @RequestParam String firstName,
                                           @RequestParam String lastName) {

        UserRole userRole = (role != UserRole.ADMIN.toString()) ? UserRole.USER : UserRole.ADMIN;

        CustomUser user = CustomUser.builder()
                .phone(phone)
                .password(password)
                .role(userRole)
                .build();
        userService.addUser(user);

        UserProfile userProfile = UserProfile.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        userProfileService.addUserProfile(userProfile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/phone/{phone}", method = RequestMethod.GET)
    public UserDTO userByPhone(
            @PathVariable(value = "phone") String phone) {
        UserDTO result = userService.findByPhone(phone);
        return result;
    }

    protected long getPageCount() {
        long totalCount = userService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
