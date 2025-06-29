package com.data.repository;

import com.data.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {
    Auth findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
