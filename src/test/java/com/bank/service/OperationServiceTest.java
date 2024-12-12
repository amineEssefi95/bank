package com.bank.service;

import com.bank.service.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class OperationServiceTest {


    @Autowired
    private OperationService operationService;

    @BeforeEach()
    public void setUp() {
        operationService = new OperationServiceImpl();
    }


    @Test
    void testDeposit() {
        operationService.deposit(1000);
        assertEquals(1, operationService.getOperations().size());
        assertEquals(1000, operationService.getOperations().getFirst().getAmount());
        assertEquals(1000, operationService.getOperations().getFirst().getBalance());
        assertEquals("DEPOSIT", operationService.getOperations().getFirst().getType());
    }


    @Test
    void testNegativeDeposit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> operationService.deposit(-1000));

        assertEquals("Deposit amount must be positive", exception.getMessage());
    }


    @Test
    void testWithdraw() {
        operationService.deposit(1000);
        operationService.withdraw(100);
        assertEquals(2, operationService.getOperations().size());
        assertEquals(900, operationService.getOperations().getLast().getBalance());
        assertEquals(100, operationService.getOperations().getLast().getAmount());
        assertEquals("WITHDRAWAL", operationService.getOperations().getLast().getType());
    }


    @Test
    void testNegativeWithdraw() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> operationService.withdraw(-500));

        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }

}
