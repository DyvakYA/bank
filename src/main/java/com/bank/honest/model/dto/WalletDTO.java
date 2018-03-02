package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Account;
import com.bank.honest.model.entity.WalletStatus;
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
    private String expired;
    private WalletStatus status;
    private Account account;
}
