package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;



@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    AccountService( AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
   
    public Account persistAccount(Account account){
        return accountRepository.save(account);
    }

    public Account getAccountByUsernameAndPassword(String username, String password, Account account){
  
         
        return account;
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    }


