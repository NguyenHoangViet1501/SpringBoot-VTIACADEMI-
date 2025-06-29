package com.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    String username;

    @Column(name = "password", nullable = false, length = 50)
    String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    String email;

    @Column(name = "address")
    String address;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "create_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "update_at")
    LocalDateTime updateAt;

    @PrePersist
    public void perPersist(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void perUpdate(){
        updateAt = LocalDateTime.now();
    }
}
