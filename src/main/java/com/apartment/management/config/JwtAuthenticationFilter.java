package com.apartment.management.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "my_secret_key_for_jwt_authentication"; // ✅ ตั้งค่า Secret Key
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService) { // ✅ Constructor รับ UserDetailsService
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("🔹 Authorization Header: " + authHeader); // ✅ LOG 1

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("🚨 ไม่มี Token หรือรูปแบบผิดพลาด!");
//            chain.doFilter(request, response);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // ✅ แก้ให้เป็น 401 Unauthorized
            return;

        }

        String token = authHeader.substring(7);
        System.out.println("✅ Token ที่รับมา: " + token); // ✅ LOG 2

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            System.out.println("✅ Username จาก Token: " + username); // ✅ LOG 3

            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("✅ Authentication ตั้งค่าสำเร็จ!");
            }
        } catch (Exception e) {
            System.out.println("🚨 Token ไม่ถูกต้อง หรือ หมดอายุ!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}
