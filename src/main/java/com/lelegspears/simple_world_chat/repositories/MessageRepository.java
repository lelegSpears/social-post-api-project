package com.lelegspears.simple_world_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lelegspears.simple_world_chat.entities.Message;

public interface MessageRepository extends JpaRepository<Message,Long>{

}
