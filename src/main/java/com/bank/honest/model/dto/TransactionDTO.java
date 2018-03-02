package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Currency;
import com.bank.honest.model.entity.TransactionStatus;
import com.bank.honest.model.entity.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date date;
    private TransactionStatus status;
    private TransactionType type;
    private long amount;
    private Currency currency;
    private long wallet_id;

}
