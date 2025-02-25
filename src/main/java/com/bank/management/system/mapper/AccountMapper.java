package com.bank.management.system.mapper;

import com.bank.management.system.entity.Account;
import com.bank.management.system.model.account.AccountResponseModel;

public interface AccountMapper {
    AccountResponseModel toResponseModel(Account account);
}
