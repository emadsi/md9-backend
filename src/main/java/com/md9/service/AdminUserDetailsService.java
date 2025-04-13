package com.md9.service;

import com.md9.model.Admin;
import com.md9.model.AdminUserDetails;
import com.md9.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("[AdminUserDetailsService] 🔍 Attempting to load user: " + username); // Logging Username loading attempt
        
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);

        if (adminOptional.isEmpty()) {
            System.out.println("[AdminUserDetailsService] ❌ Admin not found: " + username); //Logging for admin not found
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }

        Admin admin = adminOptional.get();
        System.out.println("[AdminUserDetailsService] ✅ Admin found: " + username + " | isSuperAdmin: " + admin.getIsSuperAdmin());
        
        return new AdminUserDetails(admin);
    }
}