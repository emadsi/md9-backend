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

    public boolean isSuperAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<Admin> admin = adminRepository.findByUsername(username);
            return admin.map(Admin::getIsSuperAdmin).orElse(false);
        }
        return false;
    }
}