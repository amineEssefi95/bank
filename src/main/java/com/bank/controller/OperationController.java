package com.bank.controller;

import com.bank.model.Operation;
import com.bank.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/deposit/{amount}")
    public ResponseEntity<Integer> deposit(@PathVariable Integer amount) {
        try {
            return ResponseEntity.ok(operationService.deposit(amount));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/withdraw/{amount}")
    public ResponseEntity<Integer> withdraw(@PathVariable Integer amount) {
        try {
            return ResponseEntity.ok(operationService.withdraw(amount));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/operations")
    public ResponseEntity<List<Operation>> getOperations() {
        return ResponseEntity.ok(operationService.getOperations());
    }

}
