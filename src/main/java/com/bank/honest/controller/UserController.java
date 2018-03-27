package com.bank.honest.controller;

import com.bank.honest.exception.LoginAlreadyExistException;
import com.bank.honest.model.dto.PhoneNumberCheckDTO;
import com.bank.honest.model.dto.RegistrationDTO;
import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.entity.UserRole;
import com.bank.honest.model.service.ProfileService;
import com.bank.honest.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 1/21/2018.
 */
@Slf4j
@RestController
public class UserController {

    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ProfileService profileService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDTO> users(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<UserDTO> users = userService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return users;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@Valid @RequestBody RegistrationDTO registrationDTO) {
        if (userService.existByPhone(registrationDTO.getPhone())) {
            System.out.println("qweqweqwe");
            throw new LoginAlreadyExistException("User with this phone number already exist");
        }
        userService.registration(registrationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<Void> numberCheck(@Valid @RequestBody PhoneNumberCheckDTO dto) {
        if (userService.existByPhone(dto.getPhone())) {
            throw new LoginAlreadyExistException("User with this phone number already exist");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDTO user(@PathVariable(value = "id") Long userId) {
        UserDTO result = userService.findUser(userId);
        return result;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody CustomUser user) {

        UserRole userRole = (user.getRole() != UserRole.ADMIN) ? UserRole.USER : UserRole.ADMIN;
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id", required = false) Long id) {
        userService.deleteUsers(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id[]}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam(value = "id[]", required = false) Long[] ids) {
        if (ids != null && ids.length > 0)
            userService.deleteUsers(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> userUpdate(@Valid @RequestBody CustomUser user) {

        UserRole userRole = (user.getRole() != UserRole.ADMIN) ? UserRole.USER : UserRole.ADMIN;
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/search/{phone}", method = RequestMethod.GET)
    public UserDTO userByPhone(@PathVariable(value = "phone") String phone) {
        UserDTO result = userService.findByPhone(phone);
        return result;
    }

    protected long getPageCount() {
        long totalCount = userService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
