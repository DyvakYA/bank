package com.bank.honest.model.entity.generator;

/**
 * Created by User on 6/10/2018.
 */
public class NumberGeberatorUtil {

    public static String cardNumberGenerator() {
        String s = "";
        double d;
        for (int i = 1; i <= 16; i++) {
            d = Math.random() * 10;
            s = s + ((int) d);
            if (i % 4 == 0 && i != 16) {
                s = s + "-";
            }
        }
        return s;
    }

    public static String accountNumberGenerator() {
        String s = "";
        double d;
        for (int i = 1; i <= 10; i++) {
            d = Math.random() * 10;
            s = s + ((int) d);
        }
        return s;
    }
}
