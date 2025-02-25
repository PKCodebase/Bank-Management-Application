package com.bank.management.system.mapper;

import com.bank.management.system.entity.Account;
import com.bank.management.system.entity.Transaction;
import com.bank.management.system.enums.TransactionType;
import com.bank.management.system.model.transaction.TransactionResponseModel;

public interface TransactionMapper {
    Transaction toEntity(double amount, Account account, TransactionType type);
    TransactionResponseModel toResponseModel(Long id, double amount, double balance);
}
