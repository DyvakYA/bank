package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Currency;
import com.bank.honest.model.entity.TransactionStatus;
import com.bank.honest.model.entity.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by User on 2/11/2018.
 */

@Getter
@Setter
@Builder
public class TransactionDTO {

    private Date date;
    private TransactionStatus status;
    private TransactionType type;
    private long amount;
    private Currency currency;
    private long wallet_id;

}
