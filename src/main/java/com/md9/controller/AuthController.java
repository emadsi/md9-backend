package com.md9.controller;
import com.md9.model.Admin;
import com.md9.service.AdminUserDetailsService;
import com.md9.service.AuthService;
import com.md9.service.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AdminUserDetailsService adminUserDetailsService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, AdminUserDetailsService adminUserDetailsService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.adminUserDetailsService = adminUserDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        return authService.register(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = adminUserDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }
}