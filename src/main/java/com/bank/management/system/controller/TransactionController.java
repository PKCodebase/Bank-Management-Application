package com.bank.management.system.controller;

import com.bank.management.system.model.ResponseModel;
import com.bank.management.system.model.transaction.DepositRequestModel;
import com.bank.management.system.model.transaction.TransactionResponseModel;
import com.bank.management.system.model.transaction.WithdrawRequestModel;
import com.bank.management.system.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}