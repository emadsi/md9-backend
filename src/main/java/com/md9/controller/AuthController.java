package com.md9.controller;

import com.md9.model.Admin;
import com.md9.repository.AdminRepository;
import com.md9.service.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AdminRepository adminRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AdminRepository adminRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.adminRepository = adminRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Fetch admin from DB
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
        String role = adminOptional.get().getIsSuperAdmin() ? "SUPER_ADMIN" : "ADMIN";

        if (adminOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        Admin admin = adminOptional.get();

        // Generate token
        String token = jwtUtil.generateToken(userDetails.getUsername(), role);

        // ✅ Return token and isSuperAdmin status
        return ResponseEntity.ok(Map.of(
            "token", token,
            "isSuperAdmin", admin.getIsSuperAdmin(),
            "admin", admin
        ));
    }

}