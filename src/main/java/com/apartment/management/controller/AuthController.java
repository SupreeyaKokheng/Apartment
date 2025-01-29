package com.apartment.management.controller;

import com.apartment.management.service.AuthService;
import com.apartment.management.dto.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean isAuthenticated = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            String token = authService.generateJwtToken(loginRequest.getUsername()); // ✅ ใช้ generateJwtToken()
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
    }

//    @GetMapping("/check")
//    public ResponseEntity<?> checkAuth() {
//        return ResponseEntity.ok().body("{\"message\": \"Token is valid\"}");
//    }
        @GetMapping("/check")
        public ResponseEntity<?> checkToken() {
        return ResponseEntity.ok(Map.of("message", "Token is valid"));
}



}


// package com.apartment.management.controller;

// import com.apartment.management.service.AuthService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.beans.factory.annotation.Autowired;


// import java.util.Map;

// @CrossOrigin(origins = "http://localhost:3000")
// @RestController
// @RequestMapping("/api")
// public class AuthController {

//     @Autowired
//     private AuthService authService;

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
//         String username = request.get("username");
//         String password = request.get("password");

//         boolean isAuthenticated = authService.authenticate(username, password);
//         if (isAuthenticated) {
//             return ResponseEntity.ok(Map.of("message", "Login successful"));
//         } else {
//             return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
//         }
//     }
// }
