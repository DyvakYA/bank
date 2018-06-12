package com.bank.honest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by User on 6/10/2018.
 */
@Getter
@Setter
@Builder
@ToString
public class AmountDTO {

    private Long id;
    private Long amount;
    private boolean isBlocked;
}
