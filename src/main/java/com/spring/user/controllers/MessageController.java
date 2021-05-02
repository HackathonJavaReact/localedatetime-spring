package com.spring.user.controllers;

import java.util.List;

import com.spring.user.entities.Message;
import com.spring.user.exceptions.DatabaseException;
import com.spring.user.services.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    
    @Autowired private MessageService service;

    @GetMapping
    public ResponseEntity<?> getMessages() {
        try {
            return new ResponseEntity<List<Message>>(this.service.list(), HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        try {
            return new ResponseEntity<Message>(this.service.create(message), HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/ofuser/{userId}")
    public ResponseEntity<?> getMessagesByUserId(@PathVariable Long userId) {
        try {
            return new ResponseEntity<List<Message>>(this.service.getByUserId(userId), HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
