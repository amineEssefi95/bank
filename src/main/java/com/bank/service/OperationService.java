package com.bank.service;

import com.bank.model.Operation;

import java.util.List;

public interface OperationService {

    Integer deposit(int i);
    List<Operation> getOperations();
}
