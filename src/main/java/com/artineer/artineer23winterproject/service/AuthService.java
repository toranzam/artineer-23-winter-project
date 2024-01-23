package com.artineer.artineer23winterproject.service;


import com.artineer.artineer23winterproject.dto.AccountDto;
import com.artineer.artineer23winterproject.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    public Account toEntity(AccountDto accountDto) {
        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .build();
        return account;
    }
}
