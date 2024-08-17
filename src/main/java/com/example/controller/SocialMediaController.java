package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.*;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;


    

  //Create account  
   
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<?> register(@RequestBody Account account){
           
           Account newaccount =  accountService.createNewAccount(account);

           if(newaccount == null){
          
            return ResponseEntity.status(409).body(null);
          

           }
           else if(accountRepository.findById(newaccount.getAccountId()).isPresent()){
           return ResponseEntity.status(200).body(newaccount);
           }
                
           return ResponseEntity.status(200).body(newaccount);

    }
  


 @PostMapping("/login")
public  ResponseEntity login(@RequestBody Account account) {

  Account loginAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
   
  if(loginAccount != null){

      return ResponseEntity.status(200).body(loginAccount);
     
  }
          
 return ResponseEntity.status(401).body(loginAccount);


}





//Create message
@PostMapping("/messages")
public ResponseEntity<?> createMessage(@RequestBody Message message) {

if(messageService.save(message) != null){

return   ResponseEntity.status(200).body(message); 
}

  return ResponseEntity.status(400).body(message); 
}



//Find all message
@GetMapping("/messages")
public ResponseEntity<List<Message>> getAllMessages() {
 
  return  ResponseEntity.status(200).body(messageRepository.findAll());

}




//Get message by id
@GetMapping("/messages/{messageId}")
public ResponseEntity <Message> getMessageById( Message message, @PathVariable Integer messageId) { 

  List <Message> addedMessage = new ArrayList<>();

Message messageById = messageService.findById(message, messageId);
addedMessage.add(messageById);


       return  ResponseEntity.status(200).body(messageById);  
         
}


//Delete Account
@DeleteMapping("messages/{messageId}")
public ResponseEntity<?> deleteMessage(@PathVariable("messageId") Integer messageId) {

    if(!messageRepository.findById(messageId).isEmpty()){ 
    messageRepository.deleteById(messageId);
    
    return  ResponseEntity.status(200).body(1);  
    }
   
    else{

      return ResponseEntity.status(200).body(null);
    }
  } 


//Update message
@PatchMapping("/messages/{messageId}")
public ResponseEntity<?> update(@RequestBody Message message, @PathVariable Integer messageId,String messageText) {
   

      if(messageService.updatMessage(message, messageId, messageText) != null){  
          
             return  ResponseEntity.status(200).body(1);
}

    return ResponseEntity.status(400).body(message);
    }      


@GetMapping("/accounts/{accountId}/messages")
  public ResponseEntity<List<Message>> findByPostedBy(@RequestParam Integer postedBy) {
  
         List<Message> messages = messageRepository.findByPostedBy(postedBy);

      return ResponseEntity.status(200).body(messages);


}
   

}


