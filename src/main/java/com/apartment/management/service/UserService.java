package com.apartment.management.service;

import org.springframework.stereotype.Service;
import com.apartment.management.model.User;
import com.apartment.management.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, String email) {
        String encodedPassword = passwordEncoder.encode(password); // ✅ เข้ารหัสรหัสผ่าน
        User newUser = new User(username, encodedPassword, email, "USER"); // ✅ กำหนด Role เป็น USER
        return userRepository.save(newUser);
    }
}



// package com.apartment.management.service;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.apartment.management.model.Role;
// import com.apartment.management.model.User;
// import com.apartment.management.repository.UserRepository;

// @Service
// public class UserService {

//     private final UserRepository userRepository;
//     private final BCryptPasswordEncoder passwordEncoder;

//     public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public User registerUser(String username, String password, String email, Role role) {
//         String encodedPassword = passwordEncoder.encode(password); // 🔐 เข้ารหัสรหัสผ่าน
//         User newUser = new User(username, encodedPassword, email, role);
//         return userRepository.save(newUser);
//     }
// }
