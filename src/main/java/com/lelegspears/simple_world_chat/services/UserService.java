package com.lelegspears.simple_world_chat.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
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
		try {
			if (user.getUsername() == null || user.getUsername().isBlank()) {
				throw new IllegalArgumentException("Username obrigatório");
			}

			if (repository.existsByUsername(user.getUsername())) {
				throw new IllegalArgumentException("Username já existe");
			}

			if (user.getPassword() == null || user.getPassword().isBlank()) {
				throw new IllegalArgumentException("Senha obrigatória");
			}


			user.setPassword(user.getPassword());

			return repository.save(user);

		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Username já existe");
		}
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

	public User update(Long id, User userData) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));

		updateData(entity, userData);
		return repository.save(entity);
	}

	private void updateData(User entity, User newData) {

		validateUsername(entity, newData);

		if (newData.getName() != null)
			entity.setName(newData.getName());

		if (newData.getUsername() != null)
			entity.setUsername(newData.getUsername());

		if (newData.getPassword() != null)
			entity.setPassword(newData.getPassword());
	}

	private void validateUsername(User entity, User newData) {
		if (newData.getUsername() != null &&
				repository.existsByUsername(newData.getUsername()) &&
				!entity.getUsername().equals(newData.getUsername())) {

			throw new IllegalArgumentException("Username já existe");
		}
	}
}
