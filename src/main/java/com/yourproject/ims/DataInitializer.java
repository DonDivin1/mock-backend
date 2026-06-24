package com.yourproject.ims;

import com.yourproject.ims.model.User;
import com.yourproject.ims.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("25306").isEmpty()) {
            User student = new User();
            student.setUsername("25306");
            student.setPassword("don15");
            student.setEmail("don@example.com");
            student.setFullName("Don Divin");
            student.setRole("STUDENT");
            userRepository.save(student);
            System.out.println("Seeded test student 25306");
        }
        
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setEmail("admin@example.com");
            admin.setFullName("Admin User");
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("Seeded test admin");
        }
    }
}
