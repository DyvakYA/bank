package com.bank.honest.model.dto;

import com.bank.honest.model.entity.enums.Currency;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2/11/2018.
 */


@Getter
@Setter
@Builder
public class ProductDTO {

    private Currency currency;
    private Long buyCourse;
    private Long sellCourse;
    private String description;

}
