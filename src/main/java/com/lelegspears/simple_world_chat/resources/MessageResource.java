package com.lelegspears.simple_world_chat.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lelegspears.simple_world_chat.entities.Message;
import com.lelegspears.simple_world_chat.services.MessageService;


@RestController
@RequestMapping("/messages")
public class MessageResource {
	@Autowired
	private MessageService service;
	
	@GetMapping
	public ResponseEntity<List<Message>> findAll(){
		List<Message> messages = service.findAll();
		return ResponseEntity.ok().body(messages);
	}
	
	@PostMapping
	public ResponseEntity<Message> insert(@RequestBody Message message) {
		Message msg = service.insert(message);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(msg.getId())
				.toUri();
		return ResponseEntity.created(uri).body(msg);
		
	}
	
}
