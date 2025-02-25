package com.bank.management.system.service;

import com.bank.management.system.model.account.AccountRequestModel;
import com.bank.management.system.model.account.AccountResponseModel;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {
    String createNewAccount(AccountRequestModel request);

    List<AccountResponseModel> getMyAccounts() throws AccountNotFoundException;

    String deleteAccount(Long accountId) throws AccountNotFoundException;
}
