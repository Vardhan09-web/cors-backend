package com.cors.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cors.project.model.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}

