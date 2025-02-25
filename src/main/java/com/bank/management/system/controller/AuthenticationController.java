package com.bank.management.system.controller;

import com.bank.management.system.model.authentication.AuthenticationResponseModel;
import com.bank.management.system.model.authentication.LoginRequestModel;
import com.bank.management.system.model.authentication.RegisterRequestModel;
import com.bank.management.system.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestModel request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseModel> login(@Valid @RequestBody LoginRequestModel request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}