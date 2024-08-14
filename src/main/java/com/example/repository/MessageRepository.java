package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;
import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message,Integer> {

   // @Query("FROM message WHERE postedBy =:postedBy")

    //@Query("FROM Pet WHERE species =:species")
   // List<Messages> messagesPostedBy(@Param("species") String species);
   // Message posted_by(Integer posted_by);
  //  List<Message> posted_by(@Param("postedBy") Integer postedBy);
   // Message findByPostedBy();
    List<Message> findByPostedBy(@Param("postedBy") Integer postedBy);
}
