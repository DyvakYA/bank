package com.bank.honest.security;

import lombok.*;

/**
 * Created by User on 3/18/2018.
 */

@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class JWTAuthenticateDTO {


    private String phone;
    private String password;
}
