package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.entity.Message;
import com.example.repository.MessageRepository;



@Service
public class MessageService {

  final MessageRepository messageRepository;

@Autowired
public MessageService (MessageRepository messageRepository){

      this.messageRepository = messageRepository;

    }


    public Message save(Message message) {
      if ((message.getMessageText() != "")
      && (message.getMessageText().length() <= 225) && (message.getPostedBy() != null)) {
        messageRepository.save(message);
             return message;
         }
    
         return null;
     }


     public List<Message> listAll() {

      List<Message> allMessages = messageRepository.findAll();
   
     
     return allMessages;
  }


  public Message findById(Message message,Integer messageId) {
    
    return messageRepository.findById(messageId).get();
}
 

public void delete(Integer messageId) {
  messageRepository.deleteById(messageId);
}


public Message updatMessage(Message message, Integer messageId, String messageText) {

  if ((this.messageRepository.findById(messageId) != null) && (message.getMessageText() != "")
          && (message.getMessageText().length() <= 225) && (this.messageRepository.findById(messageId).isPresent())) {  
Optional<Message> optionalMessage = messageRepository.findById(messageId);

  Message changMessageText = optionalMessage.get();
  changMessageText.setMessageText(changMessageText.getMessageText());
 return  messageRepository.save(changMessageText);

}

return null;
}


public List<Message>  findByPostBy(Integer postedBy) {

  return messageRepository.findByPostedBy(postedBy);
}


/* 
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
