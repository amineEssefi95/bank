package com.bank.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Operation {
    private final String type;
    private final Date date;
    private final Integer amount;
    private final Integer balance;

    public Operation(String type, Date date, int amount, int balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

}
