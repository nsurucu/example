package com.todo.sign.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.sign.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}