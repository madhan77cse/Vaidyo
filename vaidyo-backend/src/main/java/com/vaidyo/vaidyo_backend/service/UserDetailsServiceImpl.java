package com.vaidyo.vaidyo_backend.service;

import com.vaidyo.vaidyo_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mobileNumber)
            throws UsernameNotFoundException {

        com.vaidyo.vaidyo_backend.entity.User user =
                userRepository.findByMobileNumber(mobileNumber)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found: " + mobileNumber
                                )
                        );

        String role = "ROLE_" + user.getRole().name();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getMobileNumber())
                .password(user.getPasswordHash())
                .authorities(role)
                .build();
    }
}