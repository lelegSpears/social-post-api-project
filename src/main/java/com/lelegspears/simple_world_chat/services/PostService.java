package com.lelegspears.simple_world_chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lelegspears.simple_world_chat.entities.post.Post;
import com.lelegspears.simple_world_chat.repositories.PostRepository;
import com.lelegspears.simple_world_chat.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll(){
		List<Post> Posts = repository.findAll();
		return Posts; 
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
	
}
