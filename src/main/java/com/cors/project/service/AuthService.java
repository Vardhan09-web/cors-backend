

// package com.cors.project.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.cors.project.dto.LoginRequest;
// import com.cors.project.dto.RegisterRequest;
// import com.cors.project.model.Users;
// import com.cors.project.repository.UserRepo;
// import com.cors.project.util.JwtUtil;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// @Service
// public class AuthService {

//     @Autowired
//     private UserRepo userRepository;

//     @Autowired
//     private JwtUtil jwtUtil;

//     private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     // Register method returns just a message string
//     public String register(RegisterRequest req) {
//         if (userRepository.findByEmail(req.getEmail()).isPresent()) {
//             return "Email is already registered!";
//         }

//         if (!req.getPassword().equals(req.getRePassword())) {
//             return "Passwords do not match!";
//         }

//         Users user = new Users();
//         user.setFullName(req.getFullName());
//         user.setEmail(req.getEmail());
//         user.setPassword(passwordEncoder.encode(req.getPassword()));
//         user.setRole("USER");

//         userRepository.save(user);
//         return "User registered successfully!";
//     }

//      public List<Users> getAllUsers() {
//     return userRepository.findAll();
// }
//     // Login method now returns JSON response with ResponseEntity
//     public ResponseEntity<?> login(LoginRequest req) {
//         Optional<Users> optionalUser = userRepository.findByEmail(req.getEmail());

//         if (optionalUser.isEmpty()) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                     .body(Map.of("error", "Invalid email or password"));
//         }

//         Users user = optionalUser.get();

//         if (passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//             String token = jwtUtil.generateToken(user.getEmail());

//             Map<String, Object> response = new HashMap<>();
//             response.put("token", token);
//             response.put("email", user.getEmail());
//             response.put("role", user.getRole());
//             response.put("fullName", user.getFullName());

//             return ResponseEntity.ok(response);
//         } else {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                     .body(Map.of("error", "Invalid email or password"));
//         }
//     }
// }


package com.cors.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cors.project.dto.LoginRequest;
import com.cors.project.dto.RegisterRequest;
import com.cors.project.model.Users;
import com.cors.project.repository.UserRepo;
import com.cors.project.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register method
    public String register(RegisterRequest req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            return "Email is already registered!";
        }

        if (!req.getPassword().equals(req.getRePassword())) {
            return "Passwords do not match!";
        }

        Users user = new Users();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login method
    public ResponseEntity<?> login(LoginRequest req) {
        Optional<Users> optionalUser = userRepository.findByEmail(req.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }

        Users user = optionalUser.get();

        if (passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            response.put("fullName", user.getFullName());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }
    }
}
