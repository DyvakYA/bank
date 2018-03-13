package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Currency;
import com.bank.honest.model.entity.TransactionStatus;
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
    private String number;
    private String sourceName;
    private String destinationName;
    private Long sum;
    private Currency currency;
    private TransactionStatus status;
}
