package com.bank.honest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by User on 2/12/2018.
 */
@Getter
@Setter
@Builder
@ToString
public class ProfileDTO {

    private String email;
    private String firstName;
    private String lastName;
}
