package com.bank.management.system.service.impl;

import com.bank.management.system.entity.User;
import com.bank.management.system.exception.EmailAlreadyExistException;
import com.bank.management.system.exception.IncorrectPasswordException;
import com.bank.management.system.exception.PhoneAlreadyExistsException;
import com.bank.management.system.mapper.UserMapper;
import com.bank.management.system.model.authentication.AuthenticationResponseModel;
import com.bank.management.system.model.authentication.LoginRequestModel;
import com.bank.management.system.model.authentication.RegisterRequestModel;
import com.bank.management.system.repository.UserRepository;
import com.bank.management.system.security.JwtService;
import com.bank.management.system.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.bank.management.system.model.authentication.AuthenticationResponseModel.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterRequestModel request) {


        if (userRepository.existsByPhone(request.getPhone())) {
            throw new PhoneAlreadyExistsException("Phone number is already registered");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException("Email is already registered");
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return "Registration successful";
    }

    @Override
    public AuthenticationResponseModel login(LoginRequestModel request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailAlreadyExistException("Email does not exist"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Password is incorrect");
        }

        String token = jwtService.generateToken(user);

        return builder()
                .token(token)
                .role(user.getRole())
                .build();
    }
}
