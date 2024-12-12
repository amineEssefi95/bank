package com.bank.service.impl;

import com.bank.model.Operation;
import com.bank.service.OperationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private Integer balance = 0;
    private final List<Operation> operations = new ArrayList<>();

    @Override
    public Integer deposit(Integer amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        operations.add(new Operation("DEPOSIT", new Date(), amount, balance));

        return balance;
    }

    @Override
    public List<Operation> getOperations() {
        return operations;
    }

    @Override
    public Integer withdraw(Integer amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        operations.add(new Operation("WITHDRAWAL", new Date(), amount, balance));
        return balance;
    }
}
