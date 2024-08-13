package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message,Integer> {

}
