package com.lelegspears.simple_world_chat.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return ResponseEntity.status(201).body(msg);
		
	}
	
}
