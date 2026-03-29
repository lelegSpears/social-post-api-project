package com.lelegspears.simple_world_chat.services;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.lelegspears.simple_world_chat.entities.post.PostComment;
import com.lelegspears.simple_world_chat.repositories.PostCommentRepository;
import com.lelegspears.simple_world_chat.services.exceptions.ResourceNotFoundException;

@Service
public class PostCommentService {
	
	private final PostCommentRepository repository;
	
	public PostCommentService(PostCommentRepository repository) {
	    this.repository = repository;
	}
	
	public PostComment insert(PostComment message) {
		return repository.save(message);
	}
	
	public PostComment findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public List<PostComment> findAll() {
		return repository.findAll(); 
	}
	
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public PostComment update(Long id, PostComment pc) {
		PostComment postc = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(postc, pc);
		return repository.save(postc);
		
	}
	
	private void updateData(PostComment entity, PostComment newData) {
		entity.setContent(newData.getContent());
	}
}
