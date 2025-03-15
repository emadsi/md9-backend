package com.md9.service;


import com.md9.model.Admin;
import com.md9.repository.AdminRepository;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final AdminRepository adminRepository;


    public AuthService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;

    }

    // ✅ Register a New Admin
    // public ResponseEntity<?> register(Admin admin) {
    //     if (!isSuperAdmin()) {
    //         return ResponseEntity.status(403).body("Access Denied: Only Super Admins can register new admins");
    //     }
    //     if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
    //         return ResponseEntity.badRequest().body("Username already exists");
    //     }
    //     admin.setPassword(passwordEncoder.encode(admin.getPassword()));
    //     Admin savedAdmin = adminRepository.save(admin);
    //     return ResponseEntity.ok(savedAdmin);
    // }

    public boolean isSuperAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<Admin> admin = adminRepository.findByUsername(username);
            return admin.map(Admin::isSuperAdmin).orElse(false);
        }
        return false;
    }
}