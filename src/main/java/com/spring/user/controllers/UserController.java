package com.spring.user.controllers;

import java.util.List;

import com.spring.user.entities.User;
import com.spring.user.exceptions.DatabaseException;
import com.spring.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired UserService service;

    @GetMapping
    public ResponseEntity<?> list() {
        try {
            List<User> users = this.service.list();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        try {
            return new ResponseEntity<User>(this.service.create(user), HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
