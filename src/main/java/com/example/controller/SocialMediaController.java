package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

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


    

  //Create account  
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<List<Account>> register(@RequestBody Account account, @RequestParam String username,@RequestParam String password){
           
             accountService.createNewAccount(account, username, password);
             return ResponseEntity.status(200).body(null);

    }


//Login
@PostMapping(value = "/login" )
public @ResponseBody ResponseEntity<List<Account>> logIn(@RequestBody Account account, @PathVariable String username,@PathVariable String passwordl){

   List <Account> loginAccount = accountService.findByUsernameAndPassword(username, passwordl);

    if(accountRepository.findByUsernameAndPassword(username, passwordl).isEmpty()){
        return ResponseEntity.status(401).body(null);
    
    }
  
    return ResponseEntity.status(200).body(loginAccount);
}


//Create message
@PostMapping("/messages")
public ResponseEntity<Message> createMessage(@RequestBody Message message) {

if(!messageService.save(message).equals(null)){

return   ResponseEntity.status(200).body(message); 
}

  return ResponseEntity.status(400).body(message); 
}



//Find all message
@GetMapping("/messages")
public ResponseEntity<List<Message>> getAllMessages() {

    return ResponseEntity.status(200).body(messageService.listAll());
  
}




//Get message by id
@GetMapping("/messages/{messageId")
public ResponseEntity<Message> getMessages(@PathVariable Integer messageId) {
    try {
        Message message = messageService.getMessageById(messageId);
        return ResponseEntity.status(200).body(message);
    }
    
 catch (NoSuchElementException e) {
    return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
        
    }      
}


//Delete Account
@DeleteMapping("messages/{messageId}")
public ResponseEntity<HttpStatus> deleteMessage(@PathVariable("messageId") Integer messageId) {
  try {
    messageRepository.deleteById(messageId);

    return ResponseEntity.status(200).body(null);
  } 
  catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
  }
}


//Update message
@PatchMapping("/messages/{messageId}")
public ResponseEntity<?> update(@RequestBody Message message, @PathVariable Integer messageId) {
   

            if(!messageService.getMessageById(messageId).equals(null)){
              
                Message updatedMessage =    messageService.getMessageById(messageId);
              updatedMessage = messageService.updatMessage(message, messageId, updatedMessage.getMessageText()) ;  
          
              return ResponseEntity.status(200).body(updatedMessage);
	}
    return ResponseEntity.status(400).body("Client Error");
    }      


@GetMapping("/accounts/{accountId}/messages")
  public ResponseEntity<List<Message>> findByPostedBy(Integer postedBy) {
    try {
      List<Message> messages = messageRepository.findByPostBy(postedBy);

      if (messages.isEmpty()) {
        messages.equals(null);
        return ResponseEntity.ok(messages);
   
      }
      return ResponseEntity.status(200).body(messages);
    } 
    catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
   



}
