package com.yourproject.ims.service;

import com.yourproject.ims.model.User;
import com.yourproject.ims.repository.UserRepository;
import com.yourproject.ims.dto.LoginRequest;
import com.yourproject.ims.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public LoginResponse authenticate(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(request.getPassword())) {
            User user = userOptional.get();
            LoginResponse response = new LoginResponse();
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            response.setFullName(user.getFullName());
            response.setPermissions(user.getPermissions());
            return response;
        }
        return null;
    }
}
