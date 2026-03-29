package com.lelegspears.simple_world_chat.services;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.lelegspears.simple_world_chat.entities.chat.ChatMessage;
import com.lelegspears.simple_world_chat.repositories.ChatMessageRepository;
import com.lelegspears.simple_world_chat.services.exceptions.ResourceNotFoundException;

@Service
public class ChatMessageService {
	
	private final ChatMessageRepository repository;
	
	public ChatMessageService(ChatMessageRepository repository) {
	    this.repository = repository;
	}
	
	public ChatMessage insert(ChatMessage message) {
		return repository.save(message);
	}
	
	public ChatMessage findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<ChatMessage> findAll() {
		return repository.findAll(); 
	}
	
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public ChatMessage update(Long id, ChatMessage pc) {
		ChatMessage postc = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(postc, pc);
		return repository.save(postc);
		
	}
	
	private void updateData(ChatMessage entity, ChatMessage newData) {
		entity.setContent(newData.getContent());
	}
}
