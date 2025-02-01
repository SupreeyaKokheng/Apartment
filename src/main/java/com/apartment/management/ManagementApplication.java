package com.apartment.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		// รหัสผ่านที่ต้องการทดสอบ
		String rawPassword = "dream1234";
		String storedPassword = "$2a$10$llRHzGj.aq57sHY0v2.y.ef.ta/Dg.y7CP480lPjtDHvUFhdWzW8.";

		// ทดสอบการ match
		boolean isMatch = encoder.matches(rawPassword, storedPassword);
		System.out.println("🔍 passwordEncoder.matches: " + isMatch);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		// ทดสอบเข้ารหัสรหัสผ่านใหม่ ว่ามันแปลงแล้วจับคู่กันได้ไหม
//		String rawPassword = "dream1234";
//		String encodedPassword = encoder.encode(rawPassword);
//
//		System.out.println("🔐 Encoded Password: " + encodedPassword);
//
//		// ทดสอบเทียบกับรหัสผ่านในฐานข้อมูล
//		boolean isMatch = encoder.matches(rawPassword, "$2a$10$KXH9umow4lctMK7wYqg.EePwo0jiqs9qd3u05H.Mge2GTDDIIcXYm");
//		System.out.println("🔍 passwordEncoder.matches: " + isMatch);

//		String encodedPassword = encoder.encode("dream1234");
//		System.out.println("🔐 Encoded Password: " + encodedPassword);

	}

}
