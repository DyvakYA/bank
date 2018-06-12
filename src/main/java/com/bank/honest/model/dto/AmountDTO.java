package com.bank.honest.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 6/10/2018.
 */
@Getter
@Setter
@Builder
public class AmountDTO {

    private Long id;
    private Long amount;
    private boolean blocked;
}
