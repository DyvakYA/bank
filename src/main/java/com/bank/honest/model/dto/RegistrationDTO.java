package com.bank.honest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by User on 3/27/2018.
 */
@Getter
@Setter
@Builder
@ToString
public class RegistrationDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;

}
