package com.data.controller;

import com.data.dto.AuthCreateDTO;
import com.data.entity.Auth;
import com.data.exception.AppException;
import com.data.exception.ErrorCode;
import com.data.service.AuthService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthCreateDTO authCreateDTO){
        if(authService.UsernameExist(authCreateDTO.getUsername())){
            throw new AppException(ErrorCode.USER_EXIST);
        }
        if(authService.EmailExist(authCreateDTO.getEmail())){
            throw new AppException(ErrorCode.EMAILL_EXIST);
        }

        Auth auth = new Auth();
        auth.setUsername(authCreateDTO.getUsername());
        auth.setPassword(authCreateDTO.getPassword());
        auth.setEmail(authCreateDTO.getEmail());
        auth.setAddress(authCreateDTO.getAddress());
        auth.setDob(authCreateDTO.getDob());

        authService.register(auth);

        return new ResponseEntity<>("Register success", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestParam String username, @RequestParam String password) {
        if ((password == null || password.isBlank()) && (username == null || username.isBlank())){
            throw new AppException(ErrorCode.USERNAME_AND_PASSWORD_REQUIRED);
        }
        if (username == null || username.isBlank()) {
            throw new AppException(ErrorCode.USERNAME_REQUIRED);
        }
        if (password == null || password.isBlank()) {
            throw new AppException(ErrorCode.PASSWORD_REQUIRED);
        }

        boolean success = authService.login(username, password);
        if (!success) {
            return new ResponseEntity<>("Username or password is incorrect", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Login success", HttpStatus.OK);
    }

}
