package com.example.repository;
import com.example.entity.Account;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.Query;



@Repository
public interface AccountRepository extends JpaRepository <Account,Integer>{
  
 Account findByUsernameAndPassword(String username, String password);
}
