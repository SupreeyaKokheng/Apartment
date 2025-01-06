package com.apartment.management.config;

import com.apartment.management.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ปิดหน้า Login Default ของ Spring Security
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll() // อนุญาตให้เรียกใช้ API ได้โดยไม่ต้อง Auth
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable(); // ปิดการใช้งาน Basic Auth

        return http.build();
    }
}
