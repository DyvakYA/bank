package com.bank.honest.controller;

import com.bank.honest.model.entity.CustomUser;
import com.bank.honest.model.service.ProfileService;
import com.bank.honest.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by User on 5/10/2018.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {


    final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ProfileService profileService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<CustomUser> users(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<CustomUser> users = userService.findAllUsersForAdmin(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return users;
    }
}
