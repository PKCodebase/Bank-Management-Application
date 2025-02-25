package com.bank.management.system.controller;

import com.bank.management.system.model.account.AccountRequestModel;
import com.bank.management.system.model.account.AccountResponseModel;
import com.bank.management.system.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<String> createNewAccount(@RequestBody @Valid AccountRequestModel request) {
        return ResponseEntity.ok(accountService.createNewAccount(request)); // ✅ Only returns success message
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseModel>> getMyAccounts() throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.getMyAccounts()); // ✅ Returns only required fields
    }
}
