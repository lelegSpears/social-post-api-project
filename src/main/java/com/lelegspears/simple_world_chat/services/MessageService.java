package com.lelegspears.simple_world_chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lelegspears.simple_world_chat.entities.Message;
import com.lelegspears.simple_world_chat.repositories.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository repository;
	
	public List<Message> findAll(){
		List<Message> messages = repository.findAll();
		return messages; 
	}
	
	public Message insert(Message message) {
		return repository.save(message);
	}
}
