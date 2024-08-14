package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

//import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
//import com.example.repository.AccountRepository;

/* 
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
*/

@Service
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService (MessageRepository messageRepository){

        this.messageRepository = messageRepository;

    }

    public Message addMessage(Message message){

        return messageRepository.save(message);
    }

    public  List<Message> getAllMessage(){
  
        return messageRepository.findAll();

    }


    public Message getMessageyById(int messageId){
        
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }
        else{
            return null;
        }
    }

        public void deleteMessageById(Integer messageId){
    
               messageRepository.deleteById(messageId);
        }
    
        public Message getAllPostByOneUser(int posted_by, Message message) {

           if(message.getPostedBy().equals(posted_by)) 

               messageRepository.save(message);
           
               return message;
           
        }


     public Message updatMessage(Message message, Integer message_id, String message_text) {

            if ((this.messageRepository.findById(message_id) != null) && (message.getMessageText() != "")
                    && (message.getMessageText().length() <= 225)) {

        Optional<Message> optionalMessage = messageRepository.findById(message_id);
    
            Message changMessage = optionalMessage.get();
            message.setMessageText(changMessage.getMessageText());
            messageRepository.save(message);

            return this.messageRepository.getById(message_id);
        }

        return null;
    }


    public List<Message> getAllPostByOneUser(Integer posted_by) {
       
       // ArrayList<Message> messagesPostedBy = new ArrayList<>();
   
        return  messageRepository.findByPostedBy(posted_by);  //findByPostedBy();  //.posted_by(posted_by);  //.find//messageDAO.getAllMessageByThisUser(posted_by);
     
    }



    /*
     * /*
 * 1: Our API should be able to process new User registrations.
I should be able to create a new Account on the endpoint POST localhost:8080/register

2: Our API should be able to process User logins.
I should be able to verify my login on the endpoint POST localhost:8080/login

3: Our API should be able to process the creation of new messages.
 I should be able to submit a new post on the endpoint POST localhost:8080/messages

4: Our API should be able to retrieve all messages.
I should be able to submit a GET request on the endpoint GET localhost:8080/messages

5: Our API should be able to retrieve a message by its ID.
I should be able to submit a GET request on the endpoint GET localhost:8080/messages/{messageId}.

6: Our API should be able to delete a message identified by a message ID.
 I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{messageId}.

7: Our API should be able to update a message text identified by a message ID.
 I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}

8: Our API should be able to retrieve all messages written by a particular user.
I should be able to submit a GET request on the endpoint GET localhost:8080/accounts/{accountId}/messages.

9: The Project utilizes the Spring Framework.
     */
}
