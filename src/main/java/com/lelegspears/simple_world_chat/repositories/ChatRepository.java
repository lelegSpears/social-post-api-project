package com.lelegspears.simple_world_chat.repositories;

import com.lelegspears.simple_world_chat.entities.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long>{

}
