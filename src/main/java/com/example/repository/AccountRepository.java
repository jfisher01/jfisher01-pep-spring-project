package com.example.repository;
import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
public interface AccountRepository extends JpaRepository <Account,Integer>{
  //login
   // @Query("FROM account WHERE username =:username AND password =:password")
    Account logIntoAccount(@RequestBody Account account,@Param("username") String username,@Param("password") String password);
  
   // @Query("INSERT INTO account(username, password) VALUES(username, password)")
    Account createNewAccount(@RequestBody Account account);

   // List<Pet> lab2(@Param("name") String name, @Param("age") int age);
}
