package com.bank.management.system.service;

import com.bank.management.system.model.authentication.AuthenticationResponseModel;
import com.bank.management.system.model.authentication.RegisterRequestModel;
import com.bank.management.system.model.authentication.LoginRequestModel;

public interface AuthenticationService {
    String register(RegisterRequestModel request); // ✅ Only returns message

    AuthenticationResponseModel login(LoginRequestModel request);
}
