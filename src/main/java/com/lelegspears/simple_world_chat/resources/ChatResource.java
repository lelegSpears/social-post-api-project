package com.lelegspears.simple_world_chat.resources;

import com.lelegspears.simple_world_chat.entities.chat.Chat;
import com.lelegspears.simple_world_chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chats")
public class ChatResource {
    @Autowired
    private ChatService service;

    @GetMapping
    public ResponseEntity<List<Chat>> findAll(){
        List<Chat> messages = service.findAll();
        return ResponseEntity.ok().body(messages);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Chat> findById(@PathVariable Long id) {
        Chat chat = service.findById(id);
        return ResponseEntity.ok().body(chat);
    }

    @PostMapping
    public ResponseEntity<Chat> insert(@RequestBody Chat chat) {
        Chat cht = service.insert(chat);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cht.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cht);
    }
}
