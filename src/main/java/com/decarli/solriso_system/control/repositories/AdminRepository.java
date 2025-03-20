package com.decarli.solriso_system.control.repositories;

import com.decarli.solriso_system.model.entities.security.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
    boolean existsByEmail(String email);
}
