package com.kepco.scc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kepco.scc.repository.AccountRepository;
import com.kepco.scc.model.Account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class RegisterController {
    @Autowired 
    AccountRepository accountRepository;

    @GetMapping("/api/register")
    public List<Account> register() {
        return accountRepository.findAll();
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> registerAccount(@RequestBody Map<String, String> accountlist) {
        String userId = accountlist.get("userId");
        String password = accountlist.get("password");
        String email = accountlist.get("email");
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString();
        
        Account account = new Account();
        account.setUserId(userId);
        account.setPassword(password);
        account.setEmail(email);
        account.setRegistrationDate(now);
        accountRepository.save(account);
        
        return ResponseEntity.ok("회원가입 성공. 환영합니다.");
    }
}

