package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import java.util.*;


@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    AccountService( AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    public Account createNewAccount(Account account){

        if ((account.getUsername() != "") && (account.getPassword().length() >= 4)
        && (account.getUsername() != null) && ( accountRepository.findByUsernameAndPassword(account.getUsername(),account.getPassword()) == null)) {
        
       return accountRepository.save(account);
        }

        return null;
        }

    


    //Login to account

    public Account  getByUsernameAndPassword(String userName, String password){

          return accountRepository.findByUsernameAndPassword(userName, password);
        
                  
    }



    }


