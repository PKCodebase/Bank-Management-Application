package com.bank.management.system.service.impl;

import com.bank.management.system.entity.User;
import com.bank.management.system.exception.UserNotFoundException;
import com.bank.management.system.mapper.UserProfileMapper;
import com.bank.management.system.model.authentication.UserProfileResponseModel;
import com.bank.management.system.repository.UserRepository;
import com.bank.management.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponseModel getUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User " + email + " Not Found"));

        return userProfileMapper.toUserProfile(user);
    }
}
