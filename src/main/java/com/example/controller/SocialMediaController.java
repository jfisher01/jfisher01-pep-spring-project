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
          // accountRepository.save(newaccount);

           if(newaccount == null){
          
            return ResponseEntity.status(409).body(null);
          

           }
           else if(accountRepository.findById(newaccount.getAccountId()).isPresent()){
           return ResponseEntity.status(200).body(newaccount);
           }
                
           return ResponseEntity.status(200).body(newaccount);

    }
  


/*  
@RequestMapping("/register",method = RequestMethod.POST)
public @ResponseBody Account register(@RequestBody Account newUser) {
  Account newaccount =  accountService.createNewAccount(account, username, password);
           accountRepository.save(newaccount);

           if(newaccount.equals(null)){
               account.equals(null);
           return ResponseEntity.status(400).body(newaccount);

           }
           else if(accountRepository.findById(newaccount.getAccountId()).isPresent()){
            return ResponseEntity.status(200).body(newaccount);
           }
           else{ 
             return ResponseEntity.status(409).body(null);

    }
  
}
*/


/* 
//Login
@PostMapping(value ="/login")
public @ResponseBody ResponseEntity<List<Account>> logIn(@RequestBody Account account,@RequestParam String username,@RequestParam String password){

  //accountRepository.findByUsernameAndPassword(username, password);;
 //  List <Account> loginAccount = accountRepository.findByUsernameAndPassword(username, password);
   
  //  if(loginAccount.isEmpty()){
  
     //   return ResponseEntity.status(401).body(null);
       
   // }
    //else if( Collections.frequency(loginAccount, account.getUsername() ) >1){    
               

   //return ResponseEntity.status(409).body(loginAccount);
  
//  }
 //else{
  return null;//ResponseEntity.status(200).body(loginAccount);
 //}

}
*/

 @PostMapping("/login")
//@RequestMapping(value = "/login", method = RequestMethod.POST)
public  ResponseEntity login(@RequestBody Account account) {
  // Logic to authenticate user login
  Account loginAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());//findByUsernameAndPassword(username, password);//accountService.findByUsernameAndPassword(username, passwordl);
   
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

  List<Message> allMessages = messageService.listAll();

 if(allMessages.isEmpty()){
    allMessages.isEmpty();
    
  return   ResponseEntity.status(200).body(allMessages);
 }

    return ResponseEntity.status(200).body(allMessages);
  
}




//Get message by id
@GetMapping("/messages/{messageId")
public ResponseEntity<?> getMessageById(@PathVariable Integer messageId) {
    
        Message message = messageService.getMessageById(messageId);

        if(!message.equals(null)){
          return ResponseEntity.status(200).body(message);
        }
        return ResponseEntity.status(200).body(null);
         
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
public ResponseEntity<?> update(@RequestBody Message message, @PathVariable Integer messageId) {
   

      if(messageService.getMessageById(messageId) != null){
              
               // Message updatedMessage =    messageService.getMessageById(messageId);
              //updatedMessage = messageService.updatMessage(message, messageId, updatedMessage.getMessageText()) ;  
          
              return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
	}
    return ResponseEntity.status(400).body(message);
    }      


@GetMapping("/accounts/{accountId}/messages")
  public ResponseEntity<List<Message>> findByPostedBy(@RequestParam Integer postedBy) {
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


