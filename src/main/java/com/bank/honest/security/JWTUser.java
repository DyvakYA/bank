package com.bank.honest.security;

import com.bank.honest.model.entity.UserRole;
import lombok.*;

/**
 * Created by User on 3/19/2018.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class JWTUser {

    private Long id;
    private String phone;
    private UserRole role;

}
