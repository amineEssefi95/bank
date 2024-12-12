package com.bank.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Operation {
    private final String type;
    private final Date date;
    private final double amount;
    private final double balance;

    public Operation(String type, Date date, double amount, double balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

}
