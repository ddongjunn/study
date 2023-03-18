package com.djlee.jwt.repository;

import com.djlee.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoy extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
