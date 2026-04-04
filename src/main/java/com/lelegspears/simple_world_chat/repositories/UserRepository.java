package com.lelegspears.simple_world_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lelegspears.simple_world_chat.entities.user.User;

public interface UserRepository extends JpaRepository<User,Long>{

    boolean existsByUsername(String username);
}
