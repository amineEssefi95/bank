package com.bank.service;

import com.bank.model.Operation;

import java.util.List;

public interface OperationService {

    Integer deposit(Integer i);

    List<Operation> getOperations();

    Integer withdraw(Integer i);
}
