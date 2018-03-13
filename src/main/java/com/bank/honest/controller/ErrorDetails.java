package com.bank.honest.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by User on 3/8/2018.
 */
@Setter
@Getter
@Builder
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;


}
