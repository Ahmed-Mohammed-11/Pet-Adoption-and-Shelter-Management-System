package com.example.backend.Util;

import com.example.backend.DAO.UserRepository;
import com.example.backend.Model.users.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SecurityUtils {

    private UserRepository userRepo;

    public String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getCurrentUser() {
        String username = getCurrentUserName();
        return userRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public int getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

}