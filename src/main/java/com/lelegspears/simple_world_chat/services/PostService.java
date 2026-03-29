package com.lelegspears.simple_world_chat.services;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.lelegspears.simple_world_chat.entities.post.Post;
import com.lelegspears.simple_world_chat.repositories.PostRepository;
import com.lelegspears.simple_world_chat.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	private final PostRepository repository;
	
	public PostService(PostRepository repository) {
	    this.repository = repository;
	}
	
	public List<Post> findAll(){
		List<Post> posts = repository.findAll();
		return posts; 
	}
	
	public Post findById(Long id) {
		Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return post;
	}
	
	public Post insert(Post post) {
		return repository.save(post);
	}
	
	public void deleteById(Long id) {
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(id);
		}
	}
	
	public Post update(Long id, Post obj) {
		Post entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id));;
		updateData(entity,obj);
		return repository.save(entity);
		
	}

	private void updateData(Post entity, Post newData) {
		entity.setContent(newData.getContent());
	}
	
}
