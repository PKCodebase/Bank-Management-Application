package com.bank.management.system.service;

import com.bank.management.system.model.transaction.DepositRequestModel;
import com.bank.management.system.model.transaction.TransactionResponseModel;
import com.bank.management.system.model.transaction.WithdrawRequestModel;

public interface TransactionService {
    String deposit(DepositRequestModel request);
    TransactionResponseModel withdraw(WithdrawRequestModel request);
}