package com.bank.honest.security;

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
public class JWTAuthenticateDTO {

    private String phone;
    private String password;
}
