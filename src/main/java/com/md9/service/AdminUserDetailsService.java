package com.md9.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.md9.model.Admin;
import com.md9.model.AdminUserDetails;
import com.md9.repository.AdminRepository;

import java.util.Optional;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        
        return admin.map(AdminUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));
    }
}
