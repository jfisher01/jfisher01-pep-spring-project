package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MessageRepository messageRepository;

    private AccountService accountService;
    private MessageService messageService;


@PostMapping(value = "/register")
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<Account> register(@RequestBody Account account) {
            try {
                 Account newRegisteredAccount = accountRepository.save(new Account(account.getUsername(), 
                 account.getPassword()));
              return new ResponseEntity<>(newRegisteredAccount, HttpStatus.valueOf(200).OK);
            } catch (Exception e) {
              return new ResponseEntity<>(null, HttpStatus.valueOf(409).CONFLICT);  //CONFLICT);
            }

}

    @PostMapping(value = "/login" )
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<Account> logIn(@RequestBody Account account, @PathVariable String username,@PathVariable String passwordl){

      Account login = accountService.logIntoAccount(account, username, passwordl);
       
        return ResponseEntity.ok().body(login);
    }

  
    @PostMapping(value = "/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
                try {
                     Message newMessage = messageRepository.save(new Message(message.getPostedBy(), 
                     message.getMessageText(), message.getTimePostedEpoch()));
                  return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
                } catch (Exception e) {
                  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
    
    }


/* 
@PostMapping(value = "/messages")
@ResponseStatus(HttpStatus.CREATED)
    public Message postMessage(@RequestBody Message createdMessage) {
 
        return messageService.addMessage(createdMessage);

}
8?


@PostMapping(value = "/messages")
@ResponseStatus(HttpStatus.CREATED)
public  List<Message> getAllMessage(@RequestBody Message message) {
    
    return messageService.getAllMessage();
}

@GetMapping("/messages/{messageId}")
public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
    try {
        Message message = messageService.getMessageyById(messageId);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    } 
catch (NoSuchElementException e) {
        return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
    }      
}

/*  
@DeleteMapping("/messages/{messageId}")
public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("messageId") Integer messageId) {
  try {
    messageRepository.deleteById(messageId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

*/

@DeleteMapping("/messages/{messageId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Integer messageId) {

    messageService.deleteMessageById(messageId);  
}




@PatchMapping("/messages/{messageId}")
public ResponseEntity<Message> update(@RequestBody Message message, @PathVariable Integer messageId, @PathVariable String messageText) {
    try {
        Message existedMessage = messageService.updatMessage(message, messageId, messageText);
       
        messageRepository.save(existedMessage);
       return ResponseEntity.ok().body(existedMessage);   
    
    } catch (NoSuchElementException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } 
}

/* 

@PatchMapping("/messages/{messageId}")
public ResponseEntity<Message> updateMessage(@PathVariable("messageId") Integer messageId, @RequestBody Message message) {
  Optional<Message> messageData = messageRepository.findById(messageId);

  if (messageData.isPresent()) {
    Message updatedMessage = messageData.get();
    updatedMessage.setMessageText(message.getMessageText()); 
    updatedMessage.setPostedBy(message.getPostedBy());  
    updatedMessage.setTimePostedEpoch(message.getTimePostedEpoch()); 

    return new ResponseEntity<>(messageRepository.save(updatedMessage), HttpStatus.OK);
  } else {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
*/

@GetMapping("/accounts/{accountId}/messages")
  public ResponseEntity<List<Message>> findByPostedBy(Integer postedBy) {
    try {
      List<Message> messages = messageRepository.findByPostedBy(postedBy);

      if (messages.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(messages, HttpStatus.OK);
    } 
    catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
}