package com.bank.honest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by User on 2/11/2018.
 */

@Getter
@Setter
@Builder
@ToString
public class WalletDTO {

    private Long id;
    private String name;
    private String number;
    private String expiration;
    private boolean isBlocked;

}
