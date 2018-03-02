package com.bank.honest.model.dto;

import com.bank.honest.model.entity.Currency;
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
    private long buyCourse;
    private long sellCourse;
    private String description;

}
