package com.bank.controller;

import com.bank.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OperationControllerTest {
    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeposit() {
        int amount = 100;
        int expectedBalance = 200;
        when(operationService.deposit(amount)).thenReturn(expectedBalance);

        Integer result = operationController.deposit(amount);

        assertEquals(expectedBalance, result);
        verify(operationService, times(1)).deposit(amount);
    }

    @Test
    void testDepositThrowsIllegalArgumentException() {
        // Arrange
        int invalidAmount = -50;
        when(operationService.deposit(invalidAmount)).thenThrow(new IllegalArgumentException("Amount must be positive"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> operationController.deposit(invalidAmount));

        assertEquals("Amount must be positive", exception.getMessage());
        verify(operationService, times(1)).deposit(invalidAmount); // Ensures the service method is called
    }
}
