package com.spring.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired UserRepository repository;

    public List<User> list() throws DatabaseException {
        return this.repository.findAll();
    }

    public User create(User user) throws DatabaseException {
        return this.repository.save(user);
    }
}
