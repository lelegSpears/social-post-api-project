package com.lelegspears.simple_world_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lelegspears.simple_world_chat.entities.chat.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long>{

}
