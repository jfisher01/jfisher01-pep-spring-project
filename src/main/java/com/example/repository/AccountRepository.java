package com.example.repository;
import com.example.entity.Account;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.Query;



@Repository
public interface AccountRepository extends JpaRepository <Account,Integer>{
  
 @Query("SELECT a FROM Account a WHERE a.username =:username AND a.password =:password")
 // List<Account>  findByUsernameAndPassword(@RequestParam("username") String username,@RequestParam("password") String password);
 List<Account> findByUsernameAndPassword(String username, String password);
}
