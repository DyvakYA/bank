package com.bank.honest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by User on 3/18/2018.
 */
@Getter
@Setter
@Builder
@ToString
public class AuthenticateDTO {

    private Long id;
    private String phone;
    private String password;
}
