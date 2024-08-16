package com.example.repository;
import com.example.entity.Account;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;



@Repository
public interface AccountRepository extends JpaRepository <Account,Integer>{
  
  @Query("FROM Account WHERE username =:username AND password =:password")
  List<Account>  findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
