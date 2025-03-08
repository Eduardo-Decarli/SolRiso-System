package com.decarli.solriso_system.model.entities.security;

import com.decarli.solriso_system.control.repositories.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityAdminDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public SecurityAdminDetailService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        if(admin == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new SecurityAdminDetails(admin);
    }
}
