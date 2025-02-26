package com.md9.controller;

import com.md9.model.Admin;
import com.md9.service.AdminService;
import com.md9.service.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AdminService adminService;

    public AdminController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AdminService adminService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.adminService = adminService;
    }

    // ✅ Admin Login API
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAdmin(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            // ✅ Generate JWT Token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(Map.of("token", token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // ✅ Register New Admin API
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }
}