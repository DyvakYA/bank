package com.bank.honest.model.dto;

import com.bank.honest.model.entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
}
