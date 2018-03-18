package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by User on 2/10/2018.
 */
@Getter
@Setter
@Builder
@ToString
public class UserDTO {

    private Long id;
    private String phone;
    private String password;
    private Profile profile;

}
