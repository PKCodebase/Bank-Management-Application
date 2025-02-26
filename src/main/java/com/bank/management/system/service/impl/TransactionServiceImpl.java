package com.bank.management.system.service.impl;

import com.bank.management.system.entity.Account;
import com.bank.management.system.entity.Transaction;
import com.bank.management.system.enums.TransactionType;
import com.bank.management.system.exception.IncorrectCardNumberException;
import com.bank.management.system.exception.IncorrectCvvException;
import com.bank.management.system.exception.InsufficientFundsException;
import com.bank.management.system.exception.LowBalanceException;
import com.bank.management.system.mapper.TransactionMapper;
import com.bank.management.system.model.transaction.DepositRequestModel;
import com.bank.management.system.model.transaction.TransactionResponseModel;
import com.bank.management.system.model.transaction.WithdrawRequestModel;
import com.bank.management.system.repository.AccountRepository;
import com.bank.management.system.repository.TransactionRepository;
import com.bank.management.system.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public String deposit(DepositRequestModel request) {
        Account account = accountRepository
                .findByCardNumber(request.getCard_number())
                .filter(acc -> acc.getCardNumber().equals(request.getCard_number()))
                .orElseThrow(() -> new IncorrectCardNumberException("Incorrect card number"));


        Long transactionId = performDeposit(account, request.getAmount());


        accountRepository.save(account);

        return "Balance added successfully.";
    }

    @Override
    public TransactionResponseModel withdraw(WithdrawRequestModel request) {
        Account account = accountRepository.findByCardNumber(request.getCard_number())
                .orElseThrow(() -> new IncorrectCardNumberException("Incorrect card number"));
        if (!account.getCvv().equals(request.getCvv())) {
            throw new IncorrectCvvException("Incorrect CVV");
        }

        if (account.getBalance() < request.getAmount()) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - request.getAmount());
        accountRepository.save(account);

        return TransactionResponseModel.builder()
                .message("Balance withdrawn successfully : " + request.getAmount())
                .availableBalance(account.getBalance())
                .build();
    }

    private Long performDeposit(Account account, double amount) {
        updateAccountBalance(account, amount);
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(amount, account, TransactionType.DEPOSIT));
        return transaction.getId();
    }

    private Long performWithdrawal(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new LowBalanceException("Your Balance " + account.getBalance() + " is not enough to withdraw " + amount);
        }

        updateAccountBalance(account, -amount);
        Transaction transaction = transactionRepository.save(transactionMapper.toEntity(amount, account, TransactionType.WITHDRAW));
        return transaction.getId();
    }

    private void updateAccountBalance(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
    @Override
    public Double checkBalance(String cardNumber) {
        Account account = accountRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IncorrectCardNumberException("Incorrect card number"));

        return account.getBalance();
    }
}