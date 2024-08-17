package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.example.entity.Message;
import com.example.entity.Account;


public interface MessageRepository extends JpaRepository<Message,Integer> {

 

   List<Message> findByPostedBy(@RequestParam("postBy") Integer postedBy);

   
  
}
