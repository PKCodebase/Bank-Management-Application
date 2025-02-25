package com.bank.management.system.service.impl;

import com.bank.management.system.entity.Account;
import com.bank.management.system.entity.User;
import com.bank.management.system.exception.CardAlreadyExistException;
import com.bank.management.system.exception.UserNotFoundException;
import com.bank.management.system.mapper.AccountMapper;
import com.bank.management.system.model.account.AccountRequestModel;
import com.bank.management.system.model.account.AccountResponseModel;
import com.bank.management.system.repository.AccountRepository;
import com.bank.management.system.repository.UserRepository;
import com.bank.management.system.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public String createNewAccount(AccountRequestModel request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User " + email + " Not Found"));

        if (accountRepository.existsByCardNumber(request.getCardNumber())) {
            throw new CardAlreadyExistException("Card number already exists!");
        }

        accountRepository.save(
                Account.builder()
                        .cardNumber(request.getCardNumber())
                        .cvv(request.getCvv())
                        .balance(0.0)
                        .user(user)
                        .build()
        );

        return "Account added successfully"; // ✅ Returns only success message
    }

    @Override
    public List<AccountResponseModel> getMyAccounts() throws AccountNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("You " + email + " don't have any account"));

        return accountRepository.findAllByUser(user)
                .stream()
                .map(account -> new AccountResponseModel(account.getCardNumber(), account.getCvv(), account.getBalance()))
                .collect(Collectors.toList()); // ✅ Uses Java 8 Collectors
    }
}
