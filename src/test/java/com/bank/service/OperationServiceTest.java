package com.bank.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class OperationServiceTest {


    @Autowired
    private OperationService operationService;


    @Test
    void testDeposit() {
        operationService.deposit(1000);
        assertEquals(1, operationService.getOperations().size());
        assertEquals(1000, operationService.getOperations().getFirst().getAmount());
        assertEquals(1000, operationService.getOperations().getFirst().getBalance());
        assertEquals("DEPOSIT", operationService.getOperations().getFirst().getType());
    }

}