package com.data.service;

import com.data.entity.Auth;
import com.data.repository.AuthRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    AuthRepository authRepository;

    public boolean register(Auth auth){
        authRepository.save(auth);

        return true;
    }

    public boolean login(String username, String password){
        Auth auth = authRepository.findByUsername(username);
        if(auth == null){
            return false;
        }
        return auth.getPassword().equals(password);
    }

    public Auth findByUserName(String username){
        return authRepository.findByUsername(username);
    }

    public boolean UsernameExist(String username) {
        return authRepository.existsByUsername(username);
    }

    public boolean EmailExist(String email) {
        return authRepository.existsByEmail(email);
    }
}
