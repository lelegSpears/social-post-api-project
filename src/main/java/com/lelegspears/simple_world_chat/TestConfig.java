package com.lelegspears.simple_world_chat;

import com.lelegspears.simple_world_chat.entities.chat.Chat;
import com.lelegspears.simple_world_chat.entities.chat.ChatMessage;
import com.lelegspears.simple_world_chat.entities.enums.MessageStatus;
import com.lelegspears.simple_world_chat.entities.post.Post;
import com.lelegspears.simple_world_chat.entities.post.PostComment;
import com.lelegspears.simple_world_chat.entities.user.User;
import com.lelegspears.simple_world_chat.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    CommandLineRunner initData(
            UserRepository userRepository,
            PostRepository postRepository,
            PostCommentRepository postCommentRepository,
            ChatRepository chatRepository
    ) {
        return args -> {


            // ===== USERS =====
            User u1 = new User(null, "Leandro", "leo123", "123456");
            User u2 = new User(null, "Maria", "maria123", "123456");

            userRepository.save(u1);
            userRepository.save(u2);

            // ===== POST =====
            Post p1 = new Post(null, "Meu primeiro post!");
            p1.setUser(u1);

            postRepository.save(p1);

            // ===== COMMENT =====
            PostComment c1 = new PostComment();
            c1.setContent("Muito bom!");
            c1.setSender(u2);
            c1.setMessageStatus(MessageStatus.SENT);
            c1.setPost(p1);

            postCommentRepository.save(c1);

            // ===== CHAT =====
            Chat chat = new Chat(null, u1, u2);

            // ===== CHAT MESSAGE =====
            ChatMessage m1 = new ChatMessage();
            m1.setContent("E aí!");
            m1.setSender(u1);
            m1.setMessageStatus(MessageStatus.SENT);

            ChatMessage m2 = new ChatMessage();
            m2.setContent("Tudo bem!");
            m2.setSender(u2);
            m2.setMessageStatus(MessageStatus.RECEIVED);

            // adiciona mensagens ao chat (define o relacionamento corretamente)
            chat.addMessage(m1);
            chat.addMessage(m2);

            // salva o chat (cascade salva as mensagens automaticamente)
            chatRepository.save(chat);

            System.out.println("API iniciada com sucesso!");
        };
    }
}