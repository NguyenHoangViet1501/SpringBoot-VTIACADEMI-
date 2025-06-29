package com.data.dto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthDTO {

    int id;

    String username;

    String password;

    String email;

    String address;

    LocalDate dob;

    LocalDateTime createdAt;

    LocalDateTime updateAt;

}
