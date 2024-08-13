package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;

import org.springframework.transaction.annotation.Transactional;


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
        }else{
            return null;
        }
    }

        public void deleteMessageById(int messageId){
    
            messageRepository.deleteById(messageId);
        }
    
        public ArrayList<Message> getAllPostByOneUser(int posted_by, Message message) {

            message.getPostedBy().equals(posted_by); 

            messageRepository.save(message);
            return null;

    
        }

        public Message addSongToAlbum(Integer posted_by, Message message){
            message.getPostedBy().equals(posted_by);  //.getSongs().add(song);

            messageRepository.save(message);
            return message;

        }

    public void updateMessage(int messageId, Message changMessage){

        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            message.setMessageText(changMessage.getMessageText());
            messageRepository.save(message);
        }

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
