package com.bank.honest.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by User on 2/11/2018.
 */


@Getter
@Setter
public class ProductDTO {

    private String name;
    private long buyCourse;
    private long sellCourse;
    private String description;

    public ProductDTO() {
    }

    public ProductDTO(String name, long buyCourse, long sellCourse, String description) {
        this.name = name;
        this.buyCourse = buyCourse;
        this.sellCourse = sellCourse;
        this.description = description;
    }
}
