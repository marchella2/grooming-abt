package com.example.tugas1_marchellaps.security;

import com.example.tugas1_marchellaps.entity.User;
import com.example.tugas1_marchellaps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findByUsername(username);

        if (!findUser.isPresent()){
            throw new UsernameNotFoundException("User with username = " + username + " is not found.");
        }
        User user = findUser.get();
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
