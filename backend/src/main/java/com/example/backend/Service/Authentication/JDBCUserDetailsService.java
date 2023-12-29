package com.example.backend.Service.Authentication;

import com.example.backend.DAO.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JDBCUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        return userRepository
                .findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User %s not found", userName)
                ));
    }
}
