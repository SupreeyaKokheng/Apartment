package com.apartment.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "password123"; // 🔹 รหัสผ่านต้นฉบับ
		String encodedPassword = encoder.encode(rawPassword);
		System.out.println("🔐 Encoded Password: " + encodedPassword);
	}

}
