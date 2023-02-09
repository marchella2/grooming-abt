package com.example.tugas1_marchellaps.repository;

import com.example.tugas1_marchellaps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
