package com.bank.honest.controller;

import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by User on 1/21/2018.
 */
@Controller
public class AdminController extends BaseController {

    @Autowired
    protected UserService userService;

//    @RequestMapping("/admin/contacts")
//    public String contacts(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
//        if (page < 0) page = 0;
//        List<Contact> contacts = contactService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
//        model.addAttribute("contacts", contacts);
//        model.addAttribute("allPages", getPageCount());
//        model.addAttribute("contacts_link", "/admin/contacts");
//        model.addAttribute("users_link", "/admin/users");
//        return "contacts";
//    }

    @RequestMapping("/admin/users")
    public List<UserDTO> users(@RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<UserDTO> users = userService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        return users;
    }
}
