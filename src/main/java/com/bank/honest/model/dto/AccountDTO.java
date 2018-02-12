package com.bank.honest.model.dto;

import com.bank.honest.model.entity.CustomUser;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2/11/2018.
 */

@Getter
@Setter
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class AccountDTO {

    private String accountNumber;
    private long amount;
    private CustomUser customUser;

    public AccountDTO() {
    }

    public AccountDTO(String accountNumber, long amount, CustomUser customUser) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.customUser = customUser;
    }
}
