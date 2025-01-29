//package com.apartment.management.service;
//
//import com.apartment.management.repository.UserRepository;
//import com.apartment.management.util.JwtUtil;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//
////@Service
////public class AuthService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private JwtUtil jwtUtil; // ✅ เพิ่มการ Inject JwtUtil
////
////    public boolean authenticate(String username, String password) {
////        return userRepository.findByUsername(username)
////                .map(user -> user.getPassword().equals(password)) // ตรวจสอบ password
////                .orElse(false);
////    }
////
////    public String generateJwtToken(String username) { // ✅ สร้าง Token เมื่อ Login สำเร็จ
////        return jwtUtil.generateToken(username);
////    }
////}
//@Service
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//
//    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//    }
//
//    public boolean authenticate(String username, String password) {
//        return userRepository.findByUsername(username)
//                .map(user -> passwordEncoder.matches(password, user.getPassword()))
//                .orElse(false);
//    }
//
//    public String generateJwtToken(String username) {
//        return jwtService.generateToken(username);
//    }
//}
//
package com.apartment.management.service;

import com.apartment.management.model.User;
import com.apartment.management.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    System.out.println("🔹 รหัสผ่านที่รับมา: " + password);
                    System.out.println("🔹 รหัสผ่านที่เก็บใน DB: " + user.getPassword());

                    return passwordEncoder.matches(password, user.getPassword()); // ✅ เปรียบเทียบรหัสผ่าน
                })
                .orElse(false);
    }

    public String generateJwtToken(String username) {
        return jwtService.generateToken(username);
    }
}

//package com.apartment.management.service;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.apartment.management.repository.UserRepository;
//import com.apartment.management.util.JwtUtil;
//
//@Service
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Transactional(readOnly = true)
//    public String authenticate(String username, String password) {
//        return userRepository.findByUsername(username)
//                .filter(user -> passwordEncoder.matches(password, user.getPassword())) // ตรวจสอบรหัสผ่าน
//                .map(user -> jwtUtil.generateToken(username)) // สร้าง JWT Token
//                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
//    }
//}

// package com.apartment.management.service;

// import com.apartment.management.repository.UserRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;

// @Service
// public class AuthService {

//     @Autowired
//     private UserRepository userRepository;
//     public boolean authenticate(String username, String password) {
//         return userRepository.findByUsername(username)
//                 .map(user -> user.getPassword().equals(password)) // ตรวจสอบ password
//                 .orElse(false); // กรณี username ไม่พบ
//     }
// }
