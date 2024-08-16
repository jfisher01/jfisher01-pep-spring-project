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


    public Account createNewAccount(Account account, String username, String password){

        if ((account.getUsername() != "") && (account.getPassword().length() >= 4)
        && (account.getUsername() != null)) {
        
        return    accountRepository.save(new Account(username, password));

} 
else {
    return null;
}
    }


    //Login to account
    /* 
    public List<Account>  getByUsernameAndPassword(String userName, String password){

         List <Account> loginInfo = accountRepository.findByUsernameAndPassword(userName, password);
        
        return loginInfo;

            public User updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUser) 
    }

*/
    
    public Account  getAccountByUsernameAndPassword(String userName, String password){

         Account loginInfo = accountRepository.findByUsernameAndPassword(userName, password);
        
        return loginInfo;

            
    }



    }


