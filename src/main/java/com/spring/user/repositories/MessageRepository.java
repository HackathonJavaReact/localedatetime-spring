package com.spring.user.repositories;

import java.util.List;

import com.spring.user.entities.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByUserId(Long userId);
    
}
