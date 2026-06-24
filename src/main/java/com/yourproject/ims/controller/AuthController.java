package com.yourproject.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yourproject.ims.model.User;
import com.yourproject.ims.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/common/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(request.getPassword())) {

                Map<String, Object> response = new HashMap<>();
                response.put("username", user.getUsername());
                response.put("email", user.getEmail());
                response.put("role", user.getRole());
                response.put("permissions", user.getPermissions());
                response.put("fullName", user.getFullName());

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Username already exists"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        
        String role = request.getRole();
        if (role != null && !role.trim().isEmpty()) {
            user.setRole(role.toUpperCase().replace("ROLE_", ""));
        } else {
            user.setRole("STUDENT"); // Default role
        }
        
        user.setPermissions(java.util.List.of("READ_PRIVILEGES")); // Default permissions

        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Student registered successfully");
        response.put("username", user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

class LoginRequest {
    private String username;
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

class SignupRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

