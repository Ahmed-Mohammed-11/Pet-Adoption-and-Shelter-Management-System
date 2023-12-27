package com.example.backend.Service.Registeration;

import com.example.backend.DAO.UserRepository;
import com.example.backend.DTO.Request.RegistrationDTO;
import com.example.backend.Enums.Role;
import com.example.backend.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegistrationDTO registrationRequestDTO) {
        userRepository.save(
                User.builder()
                        .userName(registrationRequestDTO.getUserName())
                        .password(bCryptPasswordEncoder.encode(registrationRequestDTO.getPassword()))
                        .email(registrationRequestDTO.getEmail())
                        .role(Role.STAFF)
                        .build()
        );
    }
}

