package com.bank.management.system.model.authentication;

import com.bank.management.system.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseModel {
    private String token;
    private Role role;
}
