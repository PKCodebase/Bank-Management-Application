package com.bank.management.system.controller;

import com.bank.management.system.model.authentication.UserProfileResponseModel;
import com.bank.management.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserProfileResponseModel> getUserProfile() {
        return ResponseEntity.ok(userService.getUserProfile()); 
    }
}
