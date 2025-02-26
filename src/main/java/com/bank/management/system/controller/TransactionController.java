package com.bank.management.system.controller;

import com.bank.management.system.model.transaction.DepositRequestModel;
import com.bank.management.system.model.transaction.TransactionResponseModel;
import com.bank.management.system.model.transaction.WithdrawRequestModel;
import com.bank.management.system.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequestModel request) {
        String response = transactionService.deposit(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseModel> withdraw(@RequestBody WithdrawRequestModel request) {
        TransactionResponseModel response = transactionService.withdraw(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/check-balance/{cardNumber}")
    public ResponseEntity<Double> checkBalance(@PathVariable String cardNumber) {
        Double balance = transactionService.checkBalance(cardNumber);
        return ResponseEntity.ok(balance);
    }
}