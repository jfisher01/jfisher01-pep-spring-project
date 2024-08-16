package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;
import com.example.entity.Message;
import com.example.entity.Account;


public interface MessageRepository extends JpaRepository<Message,Integer> {

 
  // @Query("FROM Message  WHERE username = :username AND password =:password")
   //List<Account>  findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

   @Query("FROM Message WHERE postedBy =:postedBy")
   List<Message> findByPostBy(@Param("postBy") Integer postedBy);

}
