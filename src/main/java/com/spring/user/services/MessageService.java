package com.spring.user.services;

import java.util.List;

import com.spring.user.entities.Message;
import com.spring.user.exceptions.DatabaseException;
import com.spring.user.repositories.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    
    @Autowired MessageRepository messageRepository;
    @Autowired UserService userService;

    public List<Message> list() throws DatabaseException {
        return this.messageRepository.findAll();
    }

    public Message create(Message message) throws DatabaseException {
        if(message.getUser() != null) {
            if(!this.userService.list().contains(message.getUser())) {
                
                this.userService.create(message.getUser());
                
            } else {
                //this.userService.getById(message.getUser().getId()).getMessages().add(message);
            }
        }   
        return this.messageRepository.save(message);
    }

    public List<Message> getByUserId(Long userId) throws DatabaseException {
        return this.messageRepository.findByUserId(userId);
    }
}
