package com.lelegspears.simple_world_chat.services;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.lelegspears.simple_world_chat.entities.user.User;
import com.lelegspears.simple_world_chat.repositories.UserRepository;
import com.lelegspears.simple_world_chat.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
	    this.repository = repository;
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
	
	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<User> findAll() {
		return repository.findAll(); 
	}
	
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public User update(Long id, User pc) {
		User postc = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(postc, pc);
		return repository.save(postc);
		
	}
	
	private void updateData(User entity, User newData) {
		entity.setName(newData.getName());
	}
}
