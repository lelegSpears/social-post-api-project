package com.lelegspears.simple_world_chat.services;

import com.lelegspears.simple_world_chat.entities.chat.Chat;
import com.lelegspears.simple_world_chat.repositories.ChatRepository;
import com.lelegspears.simple_world_chat.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository repository;

    public ChatService(ChatRepository repository) {
        this.repository = repository;
    }

    public Chat insert(Chat message) {
        return repository.save(message);
    }

    public Chat findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Chat> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public Chat update(Long id, Chat newChat) {
        Chat chat = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(chat, newChat);
        return repository.save(chat);

    }

    private void updateData(Chat entity, Chat newData) {
        entity.setUser1(newData.getUser1());
        entity.setUser2(newData.getUser2());
    }
}
