package com.data.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthCreateDTO {

    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min = 2, max = 50, message = "INVALID_USERNAME_LENGTH")
    String username;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 3, max = 30, message = "INVALID_PASSWORD_LENGTH")
    String password;

    @NotBlank(message = "EMAIL_REQUIRED")
    @Size(min = 3, max = 100, message = "INVALID_EMAIL_LENGTH")
    @Email(message = "INVALID_EMAIL")
    String email;

    String address;

    @Past(message = "INVALID_DOB_FORMAT")
    LocalDate dob;

    LocalDateTime createdAt;

    LocalDateTime updateAt;
}
