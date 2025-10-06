package com.decarli.solriso_system.security;

import com.decarli.solriso_system.control.repositories.UserRepository;
import com.decarli.solriso_system.model.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return userEntity;
    }
}
