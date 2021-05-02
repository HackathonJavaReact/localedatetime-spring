package com.spring.user.services;

import java.util.List;

import com.spring.user.entities.User;
import com.spring.user.exceptions.DatabaseException;
import com.spring.user.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired UserRepository repository;

    public List<User> list() throws DatabaseException {
        return this.repository.findAll();
    }

    public User getById(Long id) throws DatabaseException {
        return this.repository.findById(id).get();
    }

    public User create(User user) throws DatabaseException {
        return this.repository.save(user);
    }
}
