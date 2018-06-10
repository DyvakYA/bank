package com.bank.honest.model.entity.enums;

/**
 * Created by User on 2/12/2018.
 */
public enum Currency {
    UAH(0),
    RUB(1),
    USD(2),
    EUR(3);


    private final int mask;

    private Currency(int mask){
        this.mask = mask;
    }

    public int getMask(){
        return mask;
    }
}
