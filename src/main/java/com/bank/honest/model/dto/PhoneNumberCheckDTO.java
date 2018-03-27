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
public class PhoneNumberCheckDTO {

    private String phone;
}
