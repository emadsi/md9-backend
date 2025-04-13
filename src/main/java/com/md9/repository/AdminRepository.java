package com.md9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.md9.model.Admin;
import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByUsername(String username);
    
    // ✅ Find the admin with the highest ID
    Optional<Admin> findTopByOrderByAdminIdDesc();
}
