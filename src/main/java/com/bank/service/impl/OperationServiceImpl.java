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
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        operations.add(new Operation("DEPOSIT", new Date(), amount, balance));
    }

    @Override
    public List<Operation> getOperations() {
        return operations;
    }
}
