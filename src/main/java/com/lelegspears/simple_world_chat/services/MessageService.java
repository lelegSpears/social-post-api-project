package com.lelegspears.simple_world_chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lelegspears.simple_world_chat.entities.message.Message;
import com.lelegspears.simple_world_chat.repositories.MessageRepository;
import com.lelegspears.simple_world_chat.services.exceptions.ResourceNotFoundException;

@Service
public class MessageService {
	@Autowired
	private MessageRepository repository;
	
	public List<Message> findAll(){
		List<Message> messages = repository.findAll();
		return messages; 
	}
	
	public Message findById(Long id) {
		Message message = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));
		return message;
	}
	
	public Message insert(Message message) {
		return repository.save(message);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(id);
			}
	}
	
	public Message update(Long id, Message obj) {
		Message entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));;
		updateData(entity,obj);
		return repository.save(entity);
		
	}

	private void updateData(Message entity, Message obj) {
		entity.setContent(obj.getContent());
		entity.setMessageStatus(obj.getMessageStatus());
	}
}
