package com.bank.management.system.mapper;

import com.bank.management.system.entity.User;
import com.bank.management.system.model.authentication.RegisterRequestModel;

public interface UserMapper {
    User toUser(RegisterRequestModel request);
}
