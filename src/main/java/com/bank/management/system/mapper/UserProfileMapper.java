package com.bank.management.system.mapper;

import com.bank.management.system.entity.User;
import com.bank.management.system.model.authentication.UserProfileResponseModel;

public interface UserProfileMapper {
    UserProfileResponseModel toUserProfile(User user);
}
