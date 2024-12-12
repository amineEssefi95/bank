package com.bank.controller;

import com.bank.model.Operation;
import com.bank.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class OperationControllerTest {
    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(operationController).build();
    }

    @Test
    void testDepositApi() throws Exception {

        Mockito.when(operationService.deposit(100)).thenReturn(600);

        mockMvc.perform(get("/deposit/{amount}", 100))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("600"));
    }

    @Test
    void testDepositApiThrowsIllegalArgumentException() throws Exception {
        Mockito.when(operationService.deposit(-100)).thenThrow(new IllegalArgumentException("Invalid amount"));

        mockMvc.perform(get("/deposit/{amount}", -100))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void testWithdrawApi() throws Exception {
        Mockito.when(operationService.withdraw(100)).thenReturn(400);

        mockMvc.perform(get("/withdraw/{amount}", 100))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("400"));
    }

    @Test
    void testWithdrawApiThrowsIllegalArgumentException() throws Exception {
        Mockito.when(operationService.withdraw(-100)).thenThrow(new IllegalArgumentException("Invalid amount"));

        mockMvc.perform(get("/withdraw/{amount}", -100))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testGetOperations() throws Exception {
        Mockito.when(operationService.getOperations()).thenReturn(List.of(new Operation("DEPOSIT", new Date(), 1000, 1000), new Operation("WITHDRAWAL", new Date(), 100, 900)));

        mockMvc.perform(get("/operations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }
}
