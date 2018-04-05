package com.bank.honest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2/11/2018.
 */

@Getter
@Setter
@Builder
public class WalletDTO {

    private String name;
    private String number;
    private String expiration;
    private long sum;
}
