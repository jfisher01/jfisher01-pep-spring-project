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


    public Account createNewAccount(Account account){

        if ((account.getUsername() != "") && (account.getPassword().length() >= 4)
        && (account.getUsername() != null)) {
    return accountRepository.save(account); 
} else {
    return null;
}
    }
  

        public Account logIntoAccount(Account account, String username, String password) {

            account = accountRepository.logIntoAccount(account, username, password);
    
                return account;
        } 
        

    }


