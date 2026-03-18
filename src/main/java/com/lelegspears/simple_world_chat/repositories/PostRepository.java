package com.lelegspears.simple_world_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lelegspears.simple_world_chat.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

}
