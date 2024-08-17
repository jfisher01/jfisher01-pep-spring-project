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
      && (message.getMessageText().length() <= 225) && (message.getPostedBy() != null) ) {
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


}
