package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.Profile;
import com.bank.honest.model.entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by User on 2/10/2018.
 */
@Getter
@Setter
@Builder
public class UserDTO {

    private String phone;
    private String password;
    private UserRole role;
    private List<Account> accounts;
    private Profile profile;

}
