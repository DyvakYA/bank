package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by User on 2/10/2018.
 */
@Getter
@Setter
public class UserDTO {

    private String phone;
    private String password;
    private UserRole role;
    private List<Account> accounts;

    public UserDTO() {}

    public UserDTO(String phone, String password, UserRole role, List<Account> accounts) {
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.accounts = accounts;
    }
}
