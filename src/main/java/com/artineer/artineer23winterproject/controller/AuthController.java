package com.artineer.artineer23winterproject.controller;


import com.artineer.artineer23winterproject.dto.AccountDto;
import com.artineer.artineer23winterproject.entity.Account;
import com.artineer.artineer23winterproject.repository.AccountRepository;
import com.artineer.artineer23winterproject.service.AuthService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AccountRepository accountRepository;

    private final AuthService authService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }


    @GetMapping("/signUp")
    public String showSignUpPage() {
        return "auth/signUp";
    }

    @PostMapping("/signUp")
    public String createNewAccount(AccountDto accountDto) {

        Account account = authService.toEntity(accountDto);

        accountRepository.save(account);

        return "redirect:/login";
    }



}
