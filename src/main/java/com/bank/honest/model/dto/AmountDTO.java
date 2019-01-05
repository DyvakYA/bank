package com.bank.honest.model.dto;

import lombok.*;

/**
 * Created by User on 6/10/2018.
 */

@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class AmountDTO {

    private Long id;
    private Long amount;
    private boolean isBlocked;
}
